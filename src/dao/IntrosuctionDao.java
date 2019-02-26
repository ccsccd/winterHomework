package dao;

public interface IntrosuctionDao {
    /**
     * 设置自我简介
     * @param userId
     * @return
     */
    boolean setIntroduction(int userId,String content);
}
