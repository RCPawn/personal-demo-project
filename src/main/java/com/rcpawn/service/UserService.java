package com.rcpawn.service;

import com.rcpawn.pojo.User;

import java.util.List;

public interface UserService {

    List<User> userList();

    int add(User user);

    User queryByNameAndPassword(String username, String password);

    User queryByUserName(String username);

    void deleteUserById(Long userId);

    void update(User user);

    User queryById(Long id);
}