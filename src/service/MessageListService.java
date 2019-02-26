package service;

import model.Message;

import java.util.List;

public interface MessageListService {
    /**
     * 获取所有的微博
     * @return
     */
    List<Message> getAllMessages();
}
