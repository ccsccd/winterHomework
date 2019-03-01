package service;

public interface UploadTextService {
    /**
     * 复制文件
     * @param srcPathStr
     * @param desPathStr
     */
    void copyFile(String srcPathStr, String desPathStr);
}
