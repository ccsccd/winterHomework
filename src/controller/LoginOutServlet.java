package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

@WebServlet("/User/LoginOut")
public class LoginOutServlet extends HttpServlet {
    private static final String OK="{\"status\":\"10000\",\"data\":\"登出成功\"}";
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        HttpSession session = req.getSession(false);
        if(session!=null){
        session.removeAttribute("已登录用户");
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                            resp.getOutputStream(),"UTF-8"
                )
        );
        writer.write(OK);
        writer.flush();
        writer.close();
        }
    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/main.html");
    }
}
