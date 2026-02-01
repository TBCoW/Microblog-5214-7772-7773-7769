package WWSIS.Microblog.dao;


public interface FollowerDao {
    void follow(int followerId, int followeeId);

    void unfollow(int followerId, int followeeId);

    boolean czyFollowuje(int followerId, int followeeId);
}
