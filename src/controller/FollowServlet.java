package controller;

import dao.FollowDao;
import dao.daoimpl.FollowDaoImpl;
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

@WebServlet("/User/FollowAction")
public class FollowServlet extends HttpServlet {
    private static final String FOLLOW="{\"status\":\"10000\",\"data\":\"关注成功\"}";
    private static final String CANCEL="{\"status\":\"10001\",\"data\":\"已取消关注\"}";
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        String topicId = req.getParameter("受关注者ID");
        String res = null;
        HttpSession session = req.getSession();
        User user = new User();
        if(session != null){
            user = (User)session.getAttribute("已登录用户");
            FollowDao followDao = new FollowDaoImpl();
            if(followDao.follow(user.getId(), Integer.parseInt(topicId))){
                res = FOLLOW;
            }else {
                res = CANCEL;
            }
        }
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        resp.getOutputStream(),"UTF-8"
                )
        );
        writer.write(res);
        writer.flush();
        writer.close();
    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/main.html");
    }
}
