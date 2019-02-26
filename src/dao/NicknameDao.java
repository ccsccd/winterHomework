package dao;

public interface NicknameDao {
    /**
     * 修改昵称
     * @param userId
     * @return
     */
    boolean setNickname(int userId,String content);
}
