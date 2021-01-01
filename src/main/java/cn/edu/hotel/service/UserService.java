package cn.edu.hotel.service;


import cn.edu.hotel.entity.User;

import java.util.List;

/**
 * 用户信息管理接口
 */
public interface UserService {

    User selectById(int userId);

    int addUser(User user);

    int insertUser(User user);

    int deleteUser(int userId);

    int updateUser(User user);

    Integer getUserCount();

    User selectByUsernameAndPassword(String username, String password);

    User selectByUsername(String username);

    List<User> selectAll();

    List<User> selectAllUser();

}
