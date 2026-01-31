package WWSIS.Microblog.dao;


public interface FollowerDao {
    void follow(int followerId, int followingId);

    void unfollow(int followerId, int followingId);

    boolean czyFollowuje(int followerId, int followingId);
}
