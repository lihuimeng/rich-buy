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
        User byUserName = userService.getByUserName(userName);
        if (null != byUserName) {
            throw new BusinessException(new ErrorInfo("-1", "用户已存在，请直接登录"));
        }

        userService.add(new User(userName, psw));
        return true;

    }

    @PostMapping("/login/{userName}/{psw}")
    public boolean login(@PathVariable String userName,
                         @PathVariable String psw) {

        User user = userService.getByUserName(userName);
        if (null == user) {
            throw new BusinessException(new ErrorInfo("-1", "用户不存在"));
        }

        String psw1 = user.getPsw();
        if (!StringUtils.equals(psw1, psw)) {
            throw new BusinessException(new ErrorInfo("-1", "账号或密码错误"));
        }
        return true;

//        String pswValue = valueOperations.get(userName);
//        if (StringUtils.isBlank(pswValue)) {
//            throw new BusinessException(new ErrorInfo("-1", "账号不存在！！"));
//        }
//        if (StringUtils.equals(psw, pswValue)) {
//            return true;
//        } else {
//            throw new BusinessException(new ErrorInfo("-1", "账号或密码错误"));
//        }
    }


}
