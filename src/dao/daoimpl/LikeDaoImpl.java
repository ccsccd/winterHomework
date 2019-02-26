package dao.daoimpl;

import db.JDBCUtil;
import dao.LikeDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static db.JDBCUtil.close;

public class LikeDaoImpl implements LikeDao {
    @Override
    public boolean like(int userId, int messageId) {
        Connection con  = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JDBCUtil.getConnection();
            pstmt = con.prepareStatement("select like_id from `like` where like_userid=? and like_messageid=?");
            pstmt.setInt(1, userId);
            pstmt.setInt(2, messageId);
            rs = pstmt.executeQuery();
            int likeId = 0;
            if(rs.next()){
                likeId = rs.getInt(1);
            }
            if(likeId != 0){
                pstmt = con.prepareStatement("delete from `like` where like_id=?");
                pstmt.setInt(1, likeId);
                pstmt.executeUpdate();
                return false;
            }else{
                pstmt = con.prepareStatement("insert into `like` values(null,?,?)");
                pstmt.setInt(1, messageId);
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
