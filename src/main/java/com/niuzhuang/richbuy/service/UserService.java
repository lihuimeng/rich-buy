package com.niuzhuang.richbuy.service;

import com.niuzhuang.richbuy.common.po.User;

/**
 * @Author: huimeng.li
 * @Description:
 * @Date: 2020/4/20 20:20
 */
public interface UserService {

    boolean add(User user);

    User getByUserName(String userName);
}
