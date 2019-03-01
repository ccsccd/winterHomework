package controller;

import dao.MessageDao;
import dao.MessageListDao;
import dao.daoimpl.MessageDaoImpl;
import dao.daoimpl.MessageListDaoImpl;
import model.Message;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

@WebServlet("/User/MessageAction")
public class MessageServlet extends HttpServlet {
    private static final String OK = "{\"status\":\"10000\",\"data\":\"发布成功\"}";
    private static final String ERROR1 = "{\"status\":\"10001\",\"data\":\"发布失败\"}";
    private static final String ERROR2 = "{\"status\":\"10002\",\"data\":\"发布内容不能为空\"}";

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("test/html;charset=utf-8");
        String webText = req.getParameter("内容");
        int parentId = Integer.parseInt((req.getParameter("父节点")).trim());
        String messageType = req.getParameter("类型");
        String res = ERROR2;
        HttpSession session = req.getSession(false);
        Message message = new Message();
        User user = new User();
        if (session != null && !webText.equals("")) {
            user = (User) session.getAttribute("已登录用户");
            message.setWebText(webText);
            message.setUserId(user.getId());
            message.setParentId(parentId);
            message.setMessageType(messageType);
            MessageDao messageDao = new MessageDaoImpl();
            if (messageDao.insertMessage(message)) {
                res = OK;
            } else {
                res = ERROR1;
            }
            MessageListDao messageListDao = new MessageListDaoImpl();
            user.setWeiboCount(messageListDao.getUserWeiboCount(user.getId()));
        }
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        resp.getOutputStream(), "UTF-8"
                )
        );
        writer.write(res);
        writer.flush();
        writer.close();
    }
        @Override
        public void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.sendRedirect(req.getContextPath() + "/main.html");
        }
    }
