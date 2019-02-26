package model;

import java.util.List;

public class Message {
    private int messageId;
    private String webText;
    private int userId;
    private int parentId;
    private String messageType;
    private int likeCount;
    private User user;
    private List<Message> childContent;
    private List<User> list;

    public Message(int messageId, String webText, int userId, int parentId, String messageType, int likeCount, User user, List<Message> childContent, List<User> list) {
        this.messageId = messageId;
        this.webText = webText;
        this.userId = userId;
        this.parentId = parentId;
        this.messageType = messageType;
        this.likeCount = likeCount;
        this.user = user;
        this.childContent = childContent;
        this.list = list;
    }

    public Message() {
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getWebText() {
        return webText;
    }

    public void setWebText(String webText) {
        this.webText = webText;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Message> getChildContent() {
        return childContent;
    }

    public void setChildContent(List<Message> childContent) {
        this.childContent = childContent;
    }

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }
}
