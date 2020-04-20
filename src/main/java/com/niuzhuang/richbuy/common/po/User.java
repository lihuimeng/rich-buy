package com.niuzhuang.richbuy.common.po;

import com.niuzhuang.richbuy.common.enums.UserTypeEnum;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "varchar(50) comment  '用户名'")
    private String userName;

    @Column(columnDefinition = "varchar(255) comment  '邮箱'")
    private String email;

    @Column(columnDefinition = "varchar(50) comment  '手机号码'")
    private String phone;

    @Column(columnDefinition = "TINYINT(1) comment  '性别（1：男，2：女）'")
    private Integer sex;

    @Column(columnDefinition = "varchar(50) comment  '用户类型'")
    private UserTypeEnum type;

    @Column(columnDefinition = "varchar(50) comment  '密码'")
    private String psw;

    public User() {

    }

    public User(String userName, String psw) {
        this.userName = userName;
        this.psw = psw;
    }
}
