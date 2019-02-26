package controller;

import dao.RegisterDao;
import dao.daoimpl.RegisterDaoImpl;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
    private static final String OK="{\"status\":\"10000\",\"data\":\"注册成功\"}";
    private static final String ERROR="{\"status\":\"10001\",\"data\":\"该手机号已被注册\"}";
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        String res = null;
        String phone = req.getParameter("手机号");
        String password = req.getParameter("密码");
        String nickname = req.getParameter("昵称");
        int byear = Integer.parseInt((req.getParameter("年份")).trim());
        int bmonth = Integer.parseInt((req.getParameter("月份")).trim());
        int bday = Integer.parseInt((req.getParameter("日期")).trim());
        User user = new User();
        user.setPhone(phone);
        user.setPassword(password);
        user.setNickname(nickname);
        user.setByear(byear);
        user.setBmonth(bmonth);
        user.setBday(bday);
        RegisterDao registerDao =new RegisterDaoImpl();
        //验证该用户名是否已被注册
        if(registerDao.checkUser(phone)) {
            res = ERROR;
        }else{
            //向数据库插入用户信息
            registerDao.insertUser(user);
            res = OK;
        }
        //返回json数据
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        resp.getOutputStream(),"UTF-8"
                )
        );
        writer.write(res);
        writer.flush();
        writer.close();
        if(res.equals(OK)){
            resp.sendRedirect(req.getContextPath() + "/success.html");
        }
    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/main.html");
    }
}
