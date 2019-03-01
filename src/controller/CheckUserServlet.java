package controller;

import dao.RegisterDao;
import dao.daoimpl.RegisterDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

@WebServlet("/CheckUser")
public class CheckUserServlet extends HttpServlet {
    private static final String OK = "{\"status\":\"10000\",\"data\":\"正确\"}";
    private static final String ERROR = "{\"status\":\"10001\",\"data\":\"该手机号已被注册\"}";
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String phone = req.getParameter("手机号");
        String res = OK;
        RegisterDao registerDao = new RegisterDaoImpl();
        //验证该用户名是否已被注册
        if (registerDao.checkUser(phone)) {
            res = ERROR;
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
}
