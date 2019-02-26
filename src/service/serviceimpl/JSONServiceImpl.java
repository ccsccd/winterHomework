package service.serviceimpl;

import model.Message;
import model.User;
import service.JSONService;

import java.util.List;

public class JSONServiceImpl implements JSONService {
    private static JSONService instance = null;
    /**
     * 得到service的单例
     * @return service
     */
    public static JSONService getInstance() {
        //双重校验锁
        if (instance == null) {
            synchronized (JSONServiceImpl.class) {
                if (instance == null) {
                    instance = new JSONServiceImpl();
                }
            }
        }
        return instance;
    }
    @Override
    public String messagesToJson(List<Message> messageList) {
        StringBuffer sb = new StringBuffer();
        sb.append("{\"status\":10000,\"data\":[");
        if (messageList != null && messageList.size() != 0) {
            for (Message content : messageList) {
                sb.append(createJson(content));
                sb.append(",");
            }
            if (sb.charAt(sb.length() - 1) == ',') {
                sb.delete(sb.length() - 1, sb.length());
            }
        }
        sb.append("]}");
        return sb.toString();
    }
    @Override
    public String usersToJson(List<User> userList) {
        StringBuffer sb = new StringBuffer();
        sb.append("{\"status\":10000,\"data\":[");
        if (userList != null && userList.size() != 0) {
            for (User content : userList) {
                sb.append(createJson2(content));
                sb.append(",");
            }
            if (sb.charAt(sb.length() - 1) == ',') {
                sb.delete(sb.length() - 1, sb.length());
            }
        }
        sb.append("]}");
        return sb.toString();
    }
    @Override
    public String messagesToJson2(List<Message> messageList) {
        StringBuffer sb = new StringBuffer();
        sb.append("{\"status\":10000,\"data\":[");
        if (messageList != null && messageList.size() != 0) {
            for (Message content : messageList) {
                sb.append(createJson3(content));
                sb.append(",");
            }
            if (sb.charAt(sb.length() - 1) == ',') {
                sb.delete(sb.length() - 1, sb.length());
            }
        }
        sb.append("]}");
        return sb.toString();
    }
    @Override
    public String usersToJson2(User user) {
        StringBuffer sb = new StringBuffer();
        sb.append("{\"status\":10000,\"data\":[");
        if (user != null) {
                sb.append(createJson4(user));
                sb.append(",");
            if (sb.charAt(sb.length() - 1) == ',') {
                sb.delete(sb.length() - 1, sb.length());
            }
        }
        sb.append("]}");
        return sb.toString();
    }
    /**
     * 产生json片段
     * @param message
     * @return
     */
    //ok
    private String createJson(Message message) {
        StringBuffer sb = new StringBuffer();
        sb.append("{\"meassageId\":").append(message.getMessageId())
                .append(",\"nickname\":\"").append(message.getUser().getNickname())
                .append("\",\"webText\":\"").append(message.getWebText())
                .append("\",\"messageType\":\"").append(message.getMessageType())
                .append("\",\"likeCount\":").append(message.getLikeCount())
                .append(",\"child\":[");
        List<Message> child = message.getChildContent();
        for (Message content : child) {
            String json = createJson(content);
            sb.append(json).append(",");
        }
        if (sb.charAt(sb.length() - 1) == ',') {
            sb.delete(sb.length() - 1, sb.length());
        }
        sb.append("]}");
        return sb.toString();
    }
    //ok
    private String createJson2(User user) {
        StringBuffer sb = new StringBuffer();
        sb.append("{\"userId\":").append(user.getId())
                .append(",\"nickname\":\"").append(user.getNickname())
                .append("\",\"followCount\":").append(user.getFollowCount())
                .append(",\"fansCount\":").append(user.getFansCount());
        if (sb.charAt(sb.length() - 1) == ',') {
            sb.delete(sb.length() - 1, sb.length());
        }
        sb.append("}");
        return sb.toString();
    }
    //ok
    private String createJson3(Message message) {
        StringBuffer sb = new StringBuffer();
        sb.append("{\"meassageId\":").append(message.getMessageId())
                .append(",\"nickname\":\"").append(message.getUser().getNickname())
                .append("\",\"webText\":\"").append(message.getWebText())
                .append("\",\"messageType\":\"").append(message.getMessageType())
                .append("\",\"likeCount\":").append(message.getLikeCount());
        if (sb.charAt(sb.length() - 1) == ',') {
            sb.delete(sb.length() - 1, sb.length());
        }
        sb.append("}");
        return sb.toString();
    }
    //ok
    private String createJson4(User user) {
        StringBuffer sb = new StringBuffer();
        sb.append("{\"userId\":").append(user.getId())
                .append(",\"nickname\":\"").append(user.getNickname())
                .append("\",\"followCount\":").append(user.getFollowCount())
                .append(",\"fansCount\":").append(user.getFansCount())
                .append(",\"introduction\":\"").append(user.getIntroduction())
                .append("\"");
        if (sb.charAt(sb.length() - 1) == ',') {
            sb.delete(sb.length() - 1, sb.length());
        }
        sb.append("}");
        return sb.toString();
    }
}
