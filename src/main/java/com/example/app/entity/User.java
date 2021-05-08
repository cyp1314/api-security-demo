package com.example.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class User extends SecurityEntity {

    private Integer id;
    private String username;
    private String password;

    public User(String sign, Integer id, String username, String password) {
        super(sign);
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
