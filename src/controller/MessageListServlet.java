package controller;

import dao.MessageListDao;
import dao.daoimpl.MessageListDaoImpl;
import model.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//该servlet已被放弃,替代品infoServlet
@WebServlet("/User/MessageListAction")
public class MessageListServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("test/html;charset=utf-8");
        MessageListDao messageListDao = new MessageListDaoImpl();
        List<Message> allMessage = messageListDao.getAllMessage();
//        req.setAttribute("全部信息", allMessage);
//        req.getRequestDispatcher(req.getContextPath() + "/main.html").forward(req, resp);
    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/main.html");
    }
}
