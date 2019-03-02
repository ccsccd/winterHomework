package controller;

import dao.LikeDao;
import dao.daoimpl.LikeDaoImpl;
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

@WebServlet("/User/LikeAction")
public class LikeServlet extends HttpServlet {
    private static final String LIKE="{\"status\":\"10000\",\"data\":\"点赞成功\"}";
    private static final String CANCEL="{\"status\":\"10001\",\"data\":\"已取消点赞\"}";
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String messageId = req.getParameter("信息ID");
        String res = null;
        HttpSession session = req.getSession();
        User user = new User();
        if(session != null){
            user = (User)session.getAttribute("已登录用户");
            LikeDao likeDao = new LikeDaoImpl();
            if(likeDao.like(user.getId(), Integer.parseInt(messageId))){
                res = LIKE;
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
