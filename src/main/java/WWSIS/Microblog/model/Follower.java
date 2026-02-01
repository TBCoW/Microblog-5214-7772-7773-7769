package WWSIS.Microblog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "FOLLOWER")
public class Follower {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "followee_id")
    private int followeeID;
    @Column(name = "follower_id")
    private int followerID;
    @Column(name = "data_obserwacji")
    private LocalDate dataObserwacji;

    public Follower(int id, int followeeID, int followerID, LocalDate dataObserwacji) {
        this.id = id;
        this.followeeID = followeeID;
        this.followerID = followerID;
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

    public int getFolloweeID() {
        return followeeID;
    }

    public void setFolloweeID(int followeeID) {
        this.followeeID = followeeID;
    }

    public int getFollowerID() {
        return followerID;
    }

    public void setFollowerID(int followerID) {
        this.followerID = followerID;
    }

    public LocalDate getDataObserwacji() {
        return dataObserwacji;
    }

    public void setDataObserwacji(LocalDate dataObserwacji) {
        this.dataObserwacji = dataObserwacji;
    }
}
