package com.niuzhuang.richbuy.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niuzhuang.richbuy.common.dto.exception.BusinessException;
import com.niuzhuang.richbuy.common.dto.exception.ErrorInfo;
import com.niuzhuang.richbuy.common.po.User;
import com.niuzhuang.richbuy.dao.UserMapper;
import com.niuzhuang.richbuy.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: huimeng.li
 * @Description:
 * @Date: 2020/4/20 20:20
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean add(User user) {
        String psw = user.getPsw();
        if (StringUtils.isBlank(psw)) {
            throw new BusinessException(new ErrorInfo("-1", "用户名不能为空"));
        }
        if (StringUtils.isBlank(user.getUserName())) {
            throw new BusinessException(new ErrorInfo("-1", "用户密码不能为空"));
        }
        if (psw.length() <6) {
            throw new BusinessException(new ErrorInfo("-1", "密码至少六位"));
        }
        return userMapper.insert(user) > 0;
    }

    @Override
    public User getByUserName(String userName) {
        if (StringUtils.isBlank(userName)) {
            throw new BusinessException(new ErrorInfo("-1", "用户名不能为空"));
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        Map<String, Object> map = new HashMap<>();
        map.put("user_name", userName);
        queryWrapper.allEq(map, false);
        return userMapper.selectOne(queryWrapper);
    }


}
