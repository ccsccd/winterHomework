package controller;

import dao.UserListDao;
import dao.daoimpl.UserListDaoImpl;
import model.User;
import service.JSONService;
import service.serviceimpl.JSONServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

@WebServlet("/UserList")
public class UserListServlet extends HttpServlet {
    JSONService jsonService;
    /**
     * 重写初始化的方法 为service赋值
     */
    @Override
    public void init() {
        jsonService= JSONServiceImpl.getInstance();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        UserListDao userListDao =new UserListDaoImpl();
        List<User> userList = userListDao.getAllUsers();
        String res = jsonService.usersToJson3(userList);
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
