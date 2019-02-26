package controller;

import dao.DeleteDao;
import dao.daoimpl.DeleteDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

@WebServlet("/User/DeleteAction")
public class DeleteServlet extends HttpServlet {
    private static final String OK="{\"status\":\"10000\",\"data\":\"删除成功\"}";
    private static final String ERROR="{\"status\":\"10001\",\"data\":\"删除失败\"}";
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        String messageId = req.getParameter("信息ID");
        String res = null;
        DeleteDao deleteDao = new DeleteDaoImpl();
        if(deleteDao.deleteMessage(Integer.parseInt(messageId))){
            res = OK;
        }else{
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
        if (res.equals(OK)){
//            resp.sendRedirect();
        }
    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/main.html");
    }
}
