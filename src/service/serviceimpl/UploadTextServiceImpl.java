package service.serviceimpl;

import service.UploadTextService;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class UploadTextServiceImpl implements UploadTextService {
    @Override
    public void copyFile(String srcPathStr, String desPathStr) {
        try{
            //创建输入输出流对象
            FileInputStream fis = new FileInputStream(srcPathStr);
            FileOutputStream fos = new FileOutputStream(desPathStr);
            //创建搬运工具
            byte datas[] = new byte[1024*8];
            //创建长度
            int len = 0;
            //循环读取数据
            while((len = fis.read(datas))!=-1){
                fos.write(datas,0,len);
            }
            //释放资源
            fis.close();
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
