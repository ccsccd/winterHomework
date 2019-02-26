package dao;

import model.User;

public interface RegisterDao {
    /**
     * 验证手机号是否已被使用
     * @param phone
     * @return
     */
    boolean checkUser(String phone);

    /**
     * 向数据库添加用户信息
     * @param user
     */
    void insertUser(User user);
}
