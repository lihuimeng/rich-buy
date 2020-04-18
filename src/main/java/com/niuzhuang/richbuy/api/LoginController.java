package com.niuzhuang.richbuy.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: huimeng.li
 * @Description:
 * @Date: 2020/4/18 16:34
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/helloworld")
    public String helloworld(@RequestParam Integer sss) throws Exception{
        String ss = null;
        boolean equals = ss.equals("22");
        return "hello world";
    }


}
