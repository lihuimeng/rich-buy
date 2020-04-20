package com.niuzhuang.richbuy.api;

import com.niuzhuang.richbuy.common.dto.exception.BusinessException;
import com.niuzhuang.richbuy.common.dto.exception.ErrorInfo;
import com.niuzhuang.richbuy.common.po.User;
import com.niuzhuang.richbuy.dao.UserMapper;
import com.niuzhuang.richbuy.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: huimeng.li
 * @Description:
 * @Date: 2020/4/18 16:34
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> valueOperations;

    @GetMapping("/helloworld")
    public String helloworld() {
        return "hello world";
    }

    @PostMapping("/register/{userName}/{psw}")
    public boolean register(@PathVariable String userName,
                            @PathVariable String psw) {
        User user = userMapper.selectById(1);
        User byUserName = userService.getByUserName(userName);
        if (null != byUserName) {
            throw new BusinessException(new ErrorInfo("-1", "用户已存在，请直接登录"));
        }

        userService.add(new User(userName, psw));
        return true;

//        final String key = userName;
//        String s = valueOperations.get(key);
//        if (StringUtils.isNotBlank(s)) {
//            throw new BusinessException(new ErrorInfo("-1", "重复注册，请直接登录！！"));
//        }
//
//        if (psw.length() <6) {
//            throw new BusinessException(new ErrorInfo("-1", "密码至少六位"));
//        }
//        valueOperations.set(userName, psw,600, TimeUnit.SECONDS);
//        return true;
    }

    @PostMapping("/login/{userName}/{psw}")
    public boolean login(@PathVariable String userName,
                         @PathVariable String psw) {
        String pswValue = valueOperations.get(userName);
        if (StringUtils.isBlank(pswValue)) {
            throw new BusinessException(new ErrorInfo("-1", "账号不存在！！"));
        }
        if (StringUtils.equals(psw, pswValue)) {
            return true;
        } else {
            throw new BusinessException(new ErrorInfo("-1", "账号或密码错误"));
        }
    }


}
