package dao.daoimpl;

import db.JDBCUtil;
import dao.MessageListDao;
import model.Message;
import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static db.JDBCUtil.close;

public class MessageListDaoImpl implements MessageListDao {
    private static MessageListDao instance = null;
    /**
     * 得到dao层的单例
     * @return dao层的单例
     */
    public static MessageListDao getInstance() {
        //双重校验锁 防止高并发的情况下new出来多个Dao的实例
        if (instance == null) {
            synchronized (MessageListDao.class) {
                if (instance == null) {
                    instance = new MessageListDaoImpl();
                }
            }
        }
        return instance;
    }
    @Override
    public List<Message> getAllMessage() {
        Connection con  = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Message> list = new ArrayList<Message>();
        try {
            con = JDBCUtil.getConnection();
            pstmt = con.prepareStatement("select u.user_nickname,m.message_webText,m.message_id,u.user_phone from message m,user u where m.message_userId = u.user_id order by m.message_id desc");
            rs = pstmt.executeQuery();
            while(rs.next()){
                Message message = new Message();
                User user = new User();
                user.setNickname(rs.getString(1));
                user.setPhone(rs.getString(4));
                message.setWebText(rs.getString(2));
                message.setMessageId(rs.getInt(3));
                message.setUser(user);
                MessageListDao messageListDao = new MessageListDaoImpl();
                int likeCount = messageListDao.getLikeCount(rs.getInt(3));
                message.setLikeCount(likeCount);
                List<User> likerNameList = messageListDao.getLikerName(rs.getInt(3));
                message.setList(likerNameList);
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
    public int getLikeCount(int messageId) {
        Connection con  = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int likeCount = 0;
        try {
            con = JDBCUtil.getConnection();
            pstmt = con.prepareStatement("select count(l.like_messageId) from `like` l where l.like_messageId = ?");
            pstmt.setInt(1, messageId);
            rs = pstmt.executeQuery();
            while(rs.next()){
                likeCount = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            close(rs, pstmt, con);
        }
        return likeCount;
    }

    @Override
    public List<User> getLikerName(int topicId) {
        Connection con  = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<User> list = new ArrayList<User>();
        try {
            con = JDBCUtil.getConnection();
            pstmt = con.prepareStatement("select u.user_nickname from `like` l inner join user u on(l.like_userId = u.user_id) where l.like_topicId = ?");
            pstmt.setInt(1, topicId);
            rs = pstmt.executeQuery();
            while(rs.next()){
                User user = new User();
                user.setNickname(rs.getString(1));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            close(rs, pstmt, con);
        }
        return list;
    }

    @Override
    public List<Message> getMessagesByPid(int parentId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Message> list = new ArrayList<Message>();
        try {
            con = JDBCUtil.getConnection();
            pstmt = con.prepareStatement("select u.user_nickname,u.user_phone,u.user_avatar,m.* from message m,user u where m.message_userId = u.user_id AND message_parentId = ? order by m.message_id desc");
            pstmt.setInt(1, parentId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Message message = new Message();
                User user = new User();
                user.setNickname(rs.getString("user_nickname"));
                user.setPhone(rs.getString("user_phone"));
                user.setAvatar(rs.getString("user_avatar"));
                message.setMessageId(rs.getInt("message_id"));
                message.setParentId(rs.getInt("message_parentId"));
                message.setWebText(rs.getString("message_webText"));
                message.setUserId(rs.getInt("message_userId"));
                message.setMessageType(rs.getString("message_type"));
                message.setTime(rs.getString("message_time"));
                message.setUser(user);
                MessageListDao messageListDao = new MessageListDaoImpl();
                int likeCount = messageListDao.getLikeCount(rs.getInt("message_id"));
                message.setLikeCount(likeCount);
                list.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, pstmt, con);
        }
        return list;
    }

    @Override
    public int getFollowCount(int userId) {
        Connection con  = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int followCount = 0;
        try {
            con = JDBCUtil.getConnection();
            pstmt = con.prepareStatement("select count(f.follow_userId) from follow f where f.follow_userId = ?");
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();
            while(rs.next()){
                followCount = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            close(rs, pstmt, con);
        }
        return followCount;
    }

    @Override
    public int getFansCount(int userId) {
        Connection con  = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int fansCount = 0;
        try {
            con = JDBCUtil.getConnection();
            pstmt = con.prepareStatement("select count(f.follow_leaderId) from follow f where f.follow_leaderId = ?");
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();
            while(rs.next()){
                fansCount = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            close(rs, pstmt, con);
        }
        return fansCount;
    }
    @Override
    public int getUserWeiboCount(int userId){
        Connection con  = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int weiboCount = 0;
        try {
            con = JDBCUtil.getConnection();
            pstmt = con.prepareStatement("select count(m.message_userId) from message m where m.message_userId = ?");
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();
            while(rs.next()){
                weiboCount = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            close(rs, pstmt, con);
        }
        return weiboCount;
    }
}
