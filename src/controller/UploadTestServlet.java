package controller;

import dao.UploadTestDao;
import dao.daoimpl.UploadTestDaoImpl;
import model.User;
import service.UploadTextService;
import service.serviceimpl.UploadTextServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;

@WebServlet("/UploadTestAction")
@MultipartConfig
public class UploadTestServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "uploadDir/images/";
    private static final String OK = "{\"status\":\"10000\",\"data\":\"修改成功\"}";
    private static final String ERROR = "{\"status\":\"10001\",\"data\":\"修改失败\"}";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String res =ERROR;
        File realPathFile = new File("G:/PP/JavaProjects/winterHomework/web/uploadDir/images/");
        if (!realPathFile.exists()) {
            realPathFile.mkdirs();
        }
        Part part = req.getPart("图片");
        String contentDisposition = part.getHeader("Content-Disposition");
        int filenameIndex = contentDisposition.indexOf("filename=");
        String filename = contentDisposition.substring(filenameIndex+10, contentDisposition.length()-1);
        String suffix=filename.substring(filename.length()-4);
        String newFileName = System.currentTimeMillis()+suffix;
        String realPath = "G:/PP/JavaProjects/winterHomework/web/uploadDir/images/";
        String filePath = realPath+newFileName;
        part.write(filePath);
        UploadTextService uploadTextService=new UploadTextServiceImpl();
        uploadTextService.copyFile(filePath,getServletContext().getRealPath("/uploadDir/images")+"/"+newFileName);
//        FileOutputStream fos = new FileOutputStream(new File(filePath));
//        //传给页面的输出流
//        ServletInputStream sis = req.getInputStream();
//        byte[] b = new byte[1024];
//        int len = 0;
//        while ((len=sis.read(b))!=-1) {
//            fos.write(b, 0, len);
//        }
//        fos.close();
//        sis.close();
        HttpSession session = req.getSession(false);
        User user = new User();
        if (session != null) {
            user = (User) session.getAttribute("已登录用户");
            user.setAvatar(UPLOAD_DIR+newFileName);
            UploadTestDao uploadTestDao = new UploadTestDaoImpl();
            if (uploadTestDao.setAvatar(user.getId(),UPLOAD_DIR+newFileName )) {
                res = OK;
            }
        }
        resp.sendRedirect(req.getContextPath() + "/user_home.html");
//        BufferedWriter writer = new BufferedWriter(
//                new OutputStreamWriter(
//                        resp.getOutputStream(),"UTF-8"
//                )
//        );
//        writer.write(res);
//        writer.write(CREATE_JSON+UPLOAD_DIR+fileName+"\"}");
//        writer.flush();
//        writer.close();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/main.html");
    }

}