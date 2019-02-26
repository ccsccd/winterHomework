package dao.daoimpl;

import dao.MessageListDao;
import dao.SearchDao;
import db.JDBCUtil;
import model.Message;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static db.JDBCUtil.close;

public class SearchDaoImpl implements SearchDao {
    private static SearchDao instance = null;
    /**
     * 得到dao层的单例
     * @return dao层的单例
     */
    public static SearchDao getInstance() {
        //双重校验锁 防止高并发的情况下new出来多个Dao的实例
        if (instance == null) {
            synchronized (LoginDaoImpl.class) {
                if (instance == null) {
                    instance = new SearchDaoImpl();
                }
            }
        }
        return instance;
    }
    @Override
    public List<Message> getNeededMessage(String keyword) {
        Connection con = null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        List<Message> list = new ArrayList<Message>();
        String str = "%" + keyword + "%";
        try {
            con= JDBCUtil.getConnection();
            pstmt = con.prepareStatement("select u.user_nickname,m.* from message m,user u where m.message_userId = u.user_id AND message_webText like ? order by m.message_id desc");
            pstmt.setString(1, str);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Message message = new Message();
                User user = new User();
                user.setNickname(rs.getString("user_nickname"));
                message.setMessageId(rs.getInt("message_id"));
                message.setWebText(rs.getString("message_webText"));
                message.setParentId(rs.getInt("message_parentId"));
                message.setMessageType(rs.getString("message_type"));
                message.setUser(user);
                MessageListDao messageListDao = new MessageListDaoImpl();
                int likeCount = messageListDao.getLikeCount(rs.getInt("message_id"));
                message.setLikeCount(likeCount);
                list.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            close(rs, pstmt, con);
        }
        return list;
    }

    @Override
    public List<User> getNeededUser(String keyword) {
        Connection con = null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        List<User> list = new ArrayList<User>();
        String str = "%" + keyword + "%";
        try {
            con= JDBCUtil.getConnection();
            pstmt = con.prepareStatement("select user_nickname,user_id from user where user_nickname like ?");
            pstmt.setString(1, str);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setNickname(rs.getString("user_nickname"));
                user.setId(rs.getInt("user_id"));
                MessageListDao messageListDao=new MessageListDaoImpl();
                user.setFollowCount(messageListDao.getFollowCount(user.getId()));
                user.setFansCount(messageListDao.getFansCount(user.getId()));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            close(rs, pstmt, con);
        }
        return list;
    }
}
