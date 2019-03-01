package dao.daoimpl;

import dao.UploadTestDao;
import db.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static db.JDBCUtil.close;

public class UploadTestDaoImpl implements UploadTestDao {
    @Override
    public boolean setAvatar(int userId, String content) {
        Connection con  = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JDBCUtil.getConnection();
            pstmt = con.prepareStatement("update user set user_avatar = ? where user_id = ?");
            pstmt.setString(1,content);
            pstmt.setInt(2,userId);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally{
            close(rs, pstmt, con);
        }
    }
}
