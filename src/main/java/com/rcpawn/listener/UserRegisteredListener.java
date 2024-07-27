package com.rcpawn.listener;

import com.rcpawn.event.UserRegisteredEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class UserRegisteredListener implements ApplicationListener<UserRegisteredEvent> {

    @Override
    public void onApplicationEvent(UserRegisteredEvent event) {
        String username = event.getUsername();
        System.out.println("用户注册成功，用户名为：" + username);
    }
}

