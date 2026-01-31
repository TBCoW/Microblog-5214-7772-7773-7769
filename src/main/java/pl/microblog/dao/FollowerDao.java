package main.java.pl.microblog.dao;


public interface FollowerDao {
    void follow(int followerId, int followeeId);

    void unfollow(int followerId, int followeeId);

    boolean czyFollowuje(int followerId, int followeeId);
}
