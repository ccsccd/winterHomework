package controller;

import dao.MessageListDao;
import dao.daoimpl.MessageListDaoImpl;
import model.User;
import service.JSONService;
import service.serviceimpl.JSONServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

@WebServlet("/User")
public class UserServlet extends HttpServlet {
    private MessageListDao messageListDao;
    JSONService jsonService;
    /**
     * 重写初始化的方法 为service赋值
     */
    @Override
    public void init() {
        messageListDao = MessageListDaoImpl.getInstance();
        jsonService= JSONServiceImpl.getInstance();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        HttpSession session = req.getSession();
        User user = new User();
        if(session != null){
            user = (User)session.getAttribute("已登录用户");
            String res = jsonService.usersToJson2(user);
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            resp.getOutputStream(),"UTF-8"
                    )
            );
            writer.write(res);
            writer.flush();
            writer.close();
        }
    }
}
