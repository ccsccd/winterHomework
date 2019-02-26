package dao;

import model.Message;
import model.User;

import java.util.List;

public interface MessageListDao {
    /**
     * 获取所有的微博
     * @param
     * @return
     */
    List<Message> getAllMessage();
    /**
     * 获取点赞总数
     * @param messageId
     * @return
     */
    int getLikeCount(int messageId);
    /**
     * 获取所有点赞人的名字
     * @param messageId
     * @return
     */
    List<User> getLikerName(int messageId);
    /**
     * 找parentId为${parentId}的的集合
     * @param parentId
     * @return
     */
    List<Message> getMessagesByPid(int parentId);

    /**
     * 获得关注总数
     * @param userId
     * @return
     */
    int getFollowCount(int userId);

    /**
     * 获得粉丝总数
     * @param userId
     * @return
     */
    int getFansCount(int userId);

    /**
     * 获得用户所发微博数
     * @param userId
     * @return
     */
    int getUserWeiboCount(int userId);
}
