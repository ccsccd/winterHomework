package dao;

import model.User;

public interface LoginDao {
    /**
     * 验证手机号和密码
     * @param phone
     * @param password
     * @return
     */
    User checkLogin(String phone, String password);
}
