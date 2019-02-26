package controller;

import model.Message;
import service.JSONService;
import service.MessageListService;
import service.serviceimpl.JSONServiceImpl;
import service.serviceimpl.MessageListServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

@WebServlet("/Info")
public class InfoServlet extends HttpServlet {
    MessageListService messageListService;
    JSONService jsonService;
    /**
     * 重写初始化的方法 为service赋值
     */
    @Override
    public void init() {
        messageListService = MessageListServiceImpl.getInstance();
        jsonService= JSONServiceImpl.getInstance();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        List<Message> messageList = messageListService.getAllMessages();
        String res = jsonService.messagesToJson(messageList);
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
