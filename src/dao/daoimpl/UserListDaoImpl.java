package dao.daoimpl;

import dao.MessageListDao;
import dao.UserListDao;
import db.JDBCUtil;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static db.JDBCUtil.close;

public class UserListDaoImpl implements UserListDao {
    @Override
    public List<User> getAllUsers() {
        Connection con  = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<User> list = new ArrayList<User>();
        try {
            con = JDBCUtil.getConnection();
            pstmt = con.prepareStatement("select * from user");
            rs = pstmt.executeQuery();
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt(1));
                user.setPhone(rs.getString(2));
                user.setNickname(rs.getString(4));
                user.setIntroduction(rs.getString(8));
                user.setAvatar(rs.getString(9));
                MessageListDao messageListDao = new MessageListDaoImpl();
                int likeCount = messageListDao.getLikeCount(rs.getInt(3));
                user.setFollowCount(messageListDao.getFollowCount(rs.getInt(1)));
                user.setFansCount(messageListDao.getFansCount(rs.getInt(1)));
                user.setWeiboCount(messageListDao.getUserWeiboCount(rs.getInt(1)));
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
