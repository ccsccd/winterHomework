package dao.daoimpl;

import dao.DeleteDao;
import db.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static db.JDBCUtil.close;

public class DeleteDaoImpl implements DeleteDao {
    @Override
    public boolean deleteMessage(int messageId) {
        Connection con  = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            con = JDBCUtil.getConnection();
            pstmt = con.prepareStatement("select like_id from `like` where like_messageId = ?");
            pstmt.setInt(1, messageId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            if (count != 0) {
                pstmt = con.prepareStatement("delete from `like` where like_messageId = ?");
                pstmt.setInt(1, messageId);
                pstmt.executeUpdate();
                count = 0;
            }

            pstmt = con.prepareStatement("select collect_id from collect where collect_messageId = ?");
            pstmt.setInt(1, messageId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            if (count != 0) {
                pstmt = con.prepareStatement("delete from collect where collect_messageId = ?");
                pstmt.setInt(1, messageId);
                pstmt.executeUpdate();
            }

            pstmt = con.prepareStatement("delete from message where message_id = ?");
            pstmt.setInt(1, messageId);
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
