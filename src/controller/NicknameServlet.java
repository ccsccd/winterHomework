package controller;

import dao.NicknameDao;
import dao.daoimpl.NicknameDaoImpl;
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

@WebServlet("/User/SetNickname")
public class NicknameServlet extends HttpServlet {
    private static final String OK = "{\"status\":\"10000\",\"data\":\"修改成功\"}";
    private static final String ERROR1 = "{\"status\":\"10001\",\"data\":\"修改失败\"}";
    private static final String ERROR2 = "{\"status\":\"10002\",\"data\":\"昵称不能为空\"}";

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String nickname = req.getParameter("昵称");
        String res = ERROR2;
        HttpSession session = req.getSession(false);
        User user = new User();
        if (session != null && !nickname.equals("")) {
            user = (User) session.getAttribute("已登录用户");
            user.setNickname(nickname);
            NicknameDao nicknameDao = new NicknameDaoImpl();
            if (nicknameDao.setNickname(user.getId(), nickname)) {
                res = OK;
            } else {
                res = ERROR1;
            }
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
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/main.html");
    }
}
