package service.serviceimpl;

import dao.MessageListDao;
import dao.daoimpl.MessageListDaoImpl;
import model.Message;
import service.MessageListService;

import java.util.List;

public class MessageListServiceImpl implements MessageListService {
    private static MessageListService instance = null;
    private MessageListDao messageListDao = null;
    /**
     * 得到service的单例
     * @return service
     */
    public static MessageListService getInstance() {
        //双重校验锁
        if (instance == null) {
            synchronized (MessageListServiceImpl.class) {
                if (instance == null) {
                    instance = new MessageListServiceImpl();
                }
            }
        }
        return instance;
    }
    /**
     * 构造方法 实例化时为Dao赋值
     */
    public MessageListServiceImpl() {
        messageListDao = MessageListDaoImpl.getInstance();
    }
    @Override
    public List<Message> getAllMessages() {
        //先找到pid为0的所有留言 即留言板上所有无父节点的留言
        List<Message> list = messageListDao.getMessagesByPid(0);
        //然后找每条留言的评论 并赋值
        for (Message message : list) {
            List<Message> childList = getContentChild(message);
            message.setChildContent(childList);
        }
        return list;
    }
    /**
     * 获取一个微博下面所有的评论
     * @param content
     * @return
     */
    private List<Message> getContentChild(Message content) {
        //找该条评论的子节点 即pid为该条评论id的评论
        List<Message> list = messageListDao.getMessagesByPid(content.getMessageId());
        //遍历它的子节点 然后递归找每条评论下的评论 即节点的子节点
        for (Message message : list) {
            //递归找这条评论的节点 然后赋值
            List<Message> childList = getContentChild(message);
            message.setChildContent(childList);
        }
        return list;
    }
}
