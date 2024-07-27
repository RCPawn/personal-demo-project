package com.rcpawn.service.impl;

import com.rcpawn.event.UserRegisteredEvent;
import com.rcpawn.mapper.UserMapper;
import com.rcpawn.pojo.User;
import com.rcpawn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public List<User> userList() {
        return userMapper.userList();
    }

    @Override
    public int add(User user) {
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        // 发布用户注册成功事件
        UserRegisteredEvent event = new UserRegisteredEvent(this, user.getUsername());
        eventPublisher.publishEvent(event);
        return userMapper.add(user);
    }

    @Override
    public User queryByNameAndPassword(String username,String password) {
        return userMapper.queryByNameAndPassword(username,password);
    }

    @Override
    public User queryByUserName(String username) {
        return userMapper.queryByUserName(username);
    }

    @Override
    public void deleteUserById(Long userId) {
        userMapper.deleteUserById(userId);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public User queryById(Long id) {
        return userMapper.queryById(id);
    }

}