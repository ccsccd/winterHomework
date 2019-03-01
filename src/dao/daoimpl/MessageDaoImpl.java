package dao.daoimpl;

import dao.MessageDao;
import db.JDBCUtil;
import model.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static db.JDBCUtil.close;

public class MessageDaoImpl implements MessageDao {
    @Override
    public boolean insertMessage(Message message) {
        Connection con  = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JDBCUtil.getConnection();
            pstmt = con.prepareStatement("insert into message values(null,?,?,?,?,?)");
            pstmt.setString(1, message.getWebText());
            pstmt.setInt(2, message.getUserId());
            pstmt.setInt(3,message.getParentId());
            pstmt.setString(4,message.getMessageType());
            Date date= new Date();
            SimpleDateFormat sd= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String now = sd.format(date);
            pstmt.setString(5,now);
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
