package dao;

public interface UploadTestDao {
    /**
     * 修改头像
     * @param userId
     * @param content
     * @return
     */
    boolean setAvatar(int userId,String content);
}
