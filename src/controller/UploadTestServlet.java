package controller;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/UploadTestServlet")
public class UploadTestServlet extends HttpServlet {
    private static final String CREATE_JSON ="{\"status\":10000,\"imageUrl\":\"";
    private static final String UPLOAD_DIR = "uploadDir/images/";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=utf-8");
        String fileName = System.currentTimeMillis()+".jpg";//保存为什么名字
        String realPath = "G:/PP/JavaProjects/winterHomework/web/uploadDir/images/";//保存的路径
        String filePath = realPath+fileName;//合起来就是完整的文件路径了
        File realPathFile = new File("G:/PP/JavaProjects/winterHomework/web/uploadDir/images/");
        if (!realPathFile.exists()) {
            realPathFile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(new File(filePath));
        //传给页面的输出流
        ServletInputStream sis = req.getInputStream();
        byte[] b = new byte[1024];
        int len = 0;
        while ((len=sis.read(b))!=-1) {
            fos.write(b, 0, len);
        }
        fos.close();
        sis.close();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        resp.getOutputStream(),"UTF-8"
                )
        );
        writer.write(CREATE_JSON+UPLOAD_DIR+fileName+"\"}");
        writer.flush();
        writer.close();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/main.html");
    }

}