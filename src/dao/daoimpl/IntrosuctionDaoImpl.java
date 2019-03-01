package dao.daoimpl;

import dao.IntrosuctionDao;
import db.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static db.JDBCUtil.close;

public class IntrosuctionDaoImpl implements IntrosuctionDao {
    @Override
    public boolean setIntroduction(int userId,String content) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JDBCUtil.getConnection();
            pstmt = con.prepareStatement("update user set user_introduction = ? where user_id = ?");
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
