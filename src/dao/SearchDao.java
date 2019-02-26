package dao;

import model.Message;
import model.User;

import java.util.List;

public interface SearchDao {
    /**
     * 获取需要的微博
     * @param keyword
     * @return
     */
    List<Message> getNeededMessage(String keyword);

    /**
     * 获取需要的用户
     * @param keyword
     * @return
     */
    List<User> getNeededUser(String keyword);
}
