package controller;

import dao.IntrosuctionDao;
import dao.daoimpl.IntrosuctionDaoImpl;
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

@WebServlet("/User/SetIntroduction")
public class IntroductionServlet extends HttpServlet {
    private static final String OK = "{\"status\":\"10000\",\"data\":\"提交成功\"}";
    private static final String ERROR = "{\"status\":\"10001\",\"data\":\"提交失败\"}";

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String introduction = req.getParameter("简介");
        String res = null;
        HttpSession session = req.getSession(false);
        User user = new User();
        if (session != null && introduction != "") {
            user = (User) session.getAttribute("已登录用户");
            user.setIntroduction(introduction);
            IntrosuctionDao introsuctionDao = new IntrosuctionDaoImpl();
            if (introsuctionDao.setIntroduction(user.getId(), introduction)) {
                res = OK;
            } else {
                res = ERROR;
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
}
