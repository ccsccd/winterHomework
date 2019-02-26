package service;

import model.Message;
import model.User;

import java.util.List;

public interface JSONService {
    /**
     * 将留言组装成json
     * @param  messageList
     * @return
     */
    String messagesToJson(List<Message> messageList);

    /**
     * 将用户组装成json
     * @param userList
     * @return
     */
    String usersToJson(List<User> userList);
    String messagesToJson2(List<Message> messageList);
    String usersToJson2(User user);
}
