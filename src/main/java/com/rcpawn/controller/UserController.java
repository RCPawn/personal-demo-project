package com.rcpawn.controller;

import com.rcpawn.pojo.User;
import com.rcpawn.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserServiceImpl userServiceImpl;

    //登录界面
    @GetMapping
    public String toLogin() {
        return "login";
    }
    @PostMapping("/login")
    public String LoginSuccess(Model model, User user) {
        User user1 = userServiceImpl.queryByNameAndPassword(user.getUsername(), user.getPassword());
        if (user1 != null) {
            log.info("用户登录：{}", user);
            return "home";
        } else {
            log.info("登录失败：{}", user);
            model.addAttribute("error", "用户名或密码错误！");
            return "login";
        }
    }

    //注册界面
    @GetMapping("/toRegister")
    public String toRegister() {
        return "register";
    }
    @RequestMapping("/register")
    public String toRegisterSuccess(Model model, User user, @RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword) {
        // 检查密码和确认密码是否匹配
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "两次输入的密码不匹配！");
            return "register"; // 返回到注册页面
        }

        // 如果密码匹配，继续执行注册逻辑
        User user2 = userServiceImpl.queryByUserName(user.getUsername());
        if (user2 == null) {
            user.setPassword(password);
            userServiceImpl.add(user);
            log.info("注册成功：{}", user);
            model.addAttribute("success", "注册成功，请登录！");
            return "login";
        } else {
            model.addAttribute("error", "用户名已存在！");
            return "register";
        }
    }

    //显示用户列表
    @RequestMapping("/userList")
    public String showUserList(Model model) {
        List<User> users = userServiceImpl.userList();
        model.addAttribute("users", users);
        log.info("查询所有用户：{}", users);
        return "userList";
    }

    //删除
    @RequestMapping("/delete")
    public String deleteUser(@RequestParam("id") Long userId) {
        userServiceImpl.deleteUserById(userId);
        log.info("删除用户：{}", userId);
        return "redirect:/users/userList";
    }

    //搜索
    @RequestMapping("/query")
    public String queryUser(Model model, User users) {
        User user = userServiceImpl.queryByUserName(users.getUsername());
        model.addAttribute("users", user);
        log.info("通过用户名查询：{}", user);
        return "userList";
    }

    //添加用户
    @RequestMapping("/toAdd")
    public String toAddUser() {
        return "/addUser";
    }
    @RequestMapping("/add")
    public String addUser(Model model, User user) {
        User user2 = userServiceImpl.queryByUserName(user.getUsername());
        if (user2 == null) {
            userServiceImpl.add(user);
            log.info("添加成功：{}", user);
            model.addAttribute("success", "添加成功！");
            return "redirect:/users/userList";
        } else {
            model.addAttribute("error", "用户名已存在！");
            return "addUser";
        }
    }

    //编辑用户
    @RequestMapping("/toEdit/{id}")
    public String toEditUser(@PathVariable("id") Long id, Model model) {
        User user = userServiceImpl.queryById(id); // 根据ID查询用户
        model.addAttribute("user", user);
        return "editUser";
    }
    @RequestMapping("/edit")
    public String editUser(@ModelAttribute("user") User user, Model model) {
        User existingUser = userServiceImpl.queryByUserName(user.getUsername());
        if (existingUser != null) {
            userServiceImpl.update(user);
            log.info("编辑成功：{}", user);
            model.addAttribute("success", "编辑成功！");
            return "redirect:/users/userList";
        } else {
            model.addAttribute("error", "不能修改用户名");
        }
        return "editUser";
    }

}
