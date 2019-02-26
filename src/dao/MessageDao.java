package dao;

import model.Message;

public interface MessageDao {
    /**
     *添加微博
     * @param message
     */
    boolean insertMessage(Message message);
}
