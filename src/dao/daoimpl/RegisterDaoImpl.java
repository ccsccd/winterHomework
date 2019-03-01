package dao.daoimpl;

import db.JDBCUtil;
import dao.RegisterDao;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static db.JDBCUtil.close;

public class RegisterDaoImpl implements RegisterDao {
    private static RegisterDao instance = null;
    /**
     * 得到dao层的单例
     * @return dao层的单例
     */
    public static RegisterDao getInstance() {
        //双重校验锁 防止高并发的情况下new出来多个Dao的实例
        if (instance == null) {
            synchronized (LoginDaoImpl.class) {
                if (instance == null) {
                    instance = new RegisterDaoImpl();
                }
            }
        }
        return instance;
    }
    @Override
    public boolean checkUser(String phone) {
        Connection con = null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            con= JDBCUtil.getConnection();
            pstmt = con.prepareStatement("select * from user where user_phone=? ");
            pstmt.setString(1, phone);
            rs = pstmt.executeQuery();
            //已被注册(查的到）返回true , 未注册（查不到）返回false
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }finally{
            close(rs, pstmt, con);
        }
    }

    @Override
    public void insertUser(User user) {
        Connection con = null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            con=JDBCUtil.getConnection();
            pstmt = con.prepareStatement("insert into user values(null,?,?,?,?,?,?,?,?)");
            pstmt.setString(1, user.getPhone());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3,user.getNickname());
            pstmt.setInt(4, user.getByear());
            pstmt.setInt(5, user.getBmonth());
            pstmt.setInt(6, user.getBday());
            pstmt.setString(7,"这个人很懒死了,什么都没有写");
            pstmt.setString(8,"images/用户圆.png");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            close(null, pstmt, con);
        }
    }
}
