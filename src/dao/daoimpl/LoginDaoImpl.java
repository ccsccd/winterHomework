package dao.daoimpl;

import dao.MessageListDao;
import db.JDBCUtil;
import dao.LoginDao;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static db.JDBCUtil.close;

public class LoginDaoImpl implements LoginDao {
    private static LoginDao instance = null;
    /**
     * 得到dao层的单例
     * @return dao层的单例
     */
    public static LoginDao getInstance() {
        //双重校验锁 防止高并发的情况下new出来多个Dao的实例
        if (instance == null) {
            synchronized (LoginDaoImpl.class) {
                if (instance == null) {
                    instance = new LoginDaoImpl();
                }
            }
        }
        return instance;
    }
    @Override
    public User checkLogin(String phone, String password) {
        Connection con = null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        User user = null;
        try {
            con=JDBCUtil.getConnection();
            pstmt = con.prepareStatement("select * from user where user_phone=? and user_password=?");
            pstmt.setString(1, phone);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            MessageListDao messageListDao=new MessageListDaoImpl();
            if (rs.next()) {
                user = new User(rs.getInt(1),rs.getString(2),rs.getString(4),
                        rs.getInt(5),rs.getInt(6),rs.getInt(7),
                        messageListDao.getFollowCount(rs.getInt(1)),messageListDao.getFansCount(rs.getInt(1)),
                        rs.getString(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            close(rs, pstmt, con);
        }
        return user;
    }
}
