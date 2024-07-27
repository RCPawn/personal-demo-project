package com.rcpawn.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String username;
    private String gender;
    private String password;
    private String type;
    private Long phone;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}