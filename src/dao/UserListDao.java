package dao;

import model.User;

import java.util.List;

public interface UserListDao {
    /**
     * 获取所有的用户
     * @return
     */
    List<User> getAllUsers();
}
