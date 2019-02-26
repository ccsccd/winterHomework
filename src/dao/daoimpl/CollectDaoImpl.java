package dao.daoimpl;

import dao.CollectDao;
import db.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static db.JDBCUtil.close;

public class CollectDaoImpl implements CollectDao {
    @Override
    public boolean collect(int userId, int messageId) {
        Connection con  = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JDBCUtil.getConnection();
            pstmt = con.prepareStatement("select collect_id from collect where collect_userid=? and collect_messageid=?");
            pstmt.setInt(1, userId);
            pstmt.setInt(2, messageId);
            rs = pstmt.executeQuery();
            int collectId = 0;
            if(rs.next()){
                collectId = rs.getInt(1);
            }
            if(collectId != 0){
                pstmt = con.prepareStatement("delete from collect where collect_id=?");
                pstmt.setInt(1, collectId);
                pstmt.executeUpdate();
                return false;
            }else{
                pstmt = con.prepareStatement("insert into collect values(null,?,?)");
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
