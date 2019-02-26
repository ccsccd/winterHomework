package dao;

public interface CollectDao {
    /**
     * 收藏微博
     * @param userId
     * @param messageId
     * @return
     */
    boolean collect(int userId,int messageId);
}
