package com.rcpawn.mapper;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.rcpawn.pojo.User;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    /**
     * 查询所有用户
     *
     * @return
     */
    @Select("select id,username,gender,type,phone,update_time from user")
    List<User> userList();

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    int add(User user);

    /**
     * 根据用户名和密码查询数据
     *
     * @param username
     * @param password
     * @return
     */
    User queryByNameAndPassword(String username, String password);

    /**
     * 根据用户名查询
     *
     * @param username
     * @return
     */
    @Select("select username,gender,type,phone from user where username = #{username}")
    User queryByUserName(String username);

    /**
     * 根据id删除用户
     *
     * @param userId
     */
    @Delete("delete from user where id = #{id}")
    void deleteUserById(Long userId);

    /**
     * 编辑用户
     *
     * @param user
     */
    void update(User user);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Select("select id,username,gender,type,phone from user where id = #{id}")
    User queryById(Long id);
}