package dao.daoimpl;

import dao.NicknameDao;
import db.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static db.JDBCUtil.close;

public class NicknameDaoImpl implements NicknameDao {
    @Override
    public boolean setNickname(int userId, String content) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JDBCUtil.getConnection();
            pstmt = con.prepareStatement("update user set user_nickname = ? where user_id = ?");
            pstmt.setString(1,content);
            pstmt.setInt(2,userId);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            close(rs, pstmt, con);
        }
    }
}
