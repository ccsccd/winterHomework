package dao;

public interface LikeDao {
    /**
     * 点赞
     * @param userId
     * @param messageId
     * @return
     */
    boolean like(int userId,int messageId);
}
