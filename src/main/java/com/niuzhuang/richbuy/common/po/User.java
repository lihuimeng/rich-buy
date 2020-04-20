package com.niuzhuang.richbuy.common.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "user")
@Data
public class User implements Serializable {

    @Column(columnDefinition = "varchar(50) comment  '用户名'")
    private String userName;

    @Column(columnDefinition = "varchar(50) comment  '密码'")
    private String psw;

    public User() {

    }

    public User(String userName, String psw) {
        this.userName = userName;
        this.psw = psw;
    }
}
