package controller;

import dao.SearchDao;
import dao.daoimpl.SearchDaoImpl;
import model.Message;
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

@WebServlet("/Search")
public class SearchServlet extends HttpServlet {
    private SearchDao searchDao = null;
    JSONService jsonService = null;
    /**
     * 重写初始化的方法 为service赋值
     */
    @Override
    public void init() {
       searchDao = SearchDaoImpl.getInstance();
       jsonService = JSONServiceImpl.getInstance();
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String type = req.getParameter("type");
        String res =null;
        if(type.equals("message")){
            String keyword = req.getParameter("key");
            List<Message> messageList = searchDao.getNeededMessage(keyword);
            res = jsonService.messagesToJson2(messageList);
        }else if(type.equals("user")) {
            String keyword = req.getParameter("key");
            List<User> userList = searchDao.getNeededUser(keyword);
            res = jsonService.usersToJson(userList);
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
