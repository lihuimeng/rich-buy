package com.niuzhuang.richbuy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.niuzhuang.richbuy.dao")
public class RichBuyApplication {

    public static void main(String[] args) {
        SpringApplication.run(RichBuyApplication.class, args);
    }

}
