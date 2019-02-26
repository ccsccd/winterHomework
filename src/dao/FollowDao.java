package dao;

public interface FollowDao {
    /**
     * 关注某人
     * @param userId
     * @param leaderId
     * @return
     */
    boolean follow(int userId,int leaderId);
}
