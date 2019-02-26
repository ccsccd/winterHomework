package dao.daoimpl;

import dao.FollowDao;
import db.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static db.JDBCUtil.close;

public class FollowDaoImpl implements FollowDao {
    @Override
    public boolean follow(int userId, int leaderId) {
        Connection con  = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JDBCUtil.getConnection();
            pstmt = con.prepareStatement("select follow_id from follow where follow_userid=? and follow_leaderid=?");
            pstmt.setInt(1, userId);
            pstmt.setInt(2, leaderId);
            rs = pstmt.executeQuery();
            int followId = 0;
            if(rs.next()){
                followId = rs.getInt(1);
            }
            if(followId != 0){
                pstmt = con.prepareStatement("delete from follow where follow_id=?");
                pstmt.setInt(1, followId);
                pstmt.executeUpdate();
                return false;
            }else{
                pstmt = con.prepareStatement("insert into follow values(null,?,?)");
                pstmt.setInt(1, leaderId);
                pstmt.setInt(2, userId);
                pstmt.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally{
            close(rs, pstmt, con);
        }
    }
}
