package main.java.pl.microblog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Followerzy")
public class Follower {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "user_id")
    private int userID;
    @Column(name = "follower_id")
    private int followerID;
    @Column(name = "following_id")
    private int followingID;
    @Column(name = "data_obserwacji")
    private double dataObserwacji;

    public Follower(int id, int userID, int followerID, int followingID, double dataObserwacji) {
        this.id = id;
        this.userID = userID;
        this.followerID = followerID;
        this.followingID = followingID;
        this.dataObserwacji = dataObserwacji;
    }

    public Follower() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getFollowerID() {
        return followerID;
    }

    public void setFollowerID(int followerID) {
        this.followerID = followerID;
    }

    public int getFollowingID() {
        return followingID;
    }

    public void setFollowingID(int followingID) {
        this.followingID = followingID;
    }

    public double getDataObserwacji() {
        return dataObserwacji;
    }

    public void setDataObserwacji(double dataObserwacji) {
        this.dataObserwacji = dataObserwacji;
    }
}
