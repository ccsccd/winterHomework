package controller;

import dao.LoginDao;
import dao.RegisterDao;
import dao.daoimpl.LoginDaoImpl;
import dao.daoimpl.RegisterDaoImpl;
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

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    private LoginDao loginDao = null;
    private RegisterDao registerDao =null;
    /**
     * 重写初始化的方法
     */
    @Override
    public void init() {
        loginDao = LoginDaoImpl.getInstance();
        registerDao = RegisterDaoImpl.getInstance();
    }
    private static final String OK = "{\"status\":\"10000\",\"data\":\"登录成功\"}";
    private static final String ERROR1 = "{\"status\":\"10001\",\"data\":\"用户名或密码错误\"}";
    private static final String ERROR2 = "{\"status\":\"10002\",\"data\":\"该用户不存在\"}";
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String phone = req.getParameter("手机号");
        String password = req.getParameter("密码");
        String res = ERROR1;
        HttpSession session = req.getSession();
        User user = null;
        //验证该用户名是否存在
        if (!registerDao.checkUser(phone)) {
            res = ERROR2;
        }else {
            //验证密码是否正确
            user = loginDao.checkLogin(phone, password);
            if (user != null) {
                session.setAttribute("已登录用户", user);
                res = OK;
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
