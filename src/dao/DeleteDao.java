package dao;

public interface DeleteDao {
    /**
     * 删除用户自己发的微博
     * @param messageId
     */
    boolean deleteMessage(int messageId);
}
