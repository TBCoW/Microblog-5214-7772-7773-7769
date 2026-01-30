package main.java.pl.microblog.dao.impl;

import main.java.pl.microblog.dao.FollowerDao;
import main.java.pl.microblog.model.Follower;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings("unchecked")
@Transactional
public class FollowerDaoImpl implements FollowerDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void follow(int followerId, int followingId) {
        Follower f = new Follower();
        f.setFollowerID(followerId);
        f.setFollowingID(followingId);

        entityManager.persist(f);
    }

    @Override
    public void unfollow(int followerId, int followingId) {
        String queryString = "DELETE FROM Follower f WHERE f.followerID = :followerId AND f.followingID = :followingId";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("followerId", followerId);
        query.setParameter("followingId", followingId);

        query.executeUpdate();
    }

    @Override
    public boolean czyFollowuje(int followerId, int followingId) {
        String queryString = "SELECT COUNT(f) FROM Follower f WHERE f.followerID = :followerId AND f.followingID = :followingId";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("followerId", followerId);
        query.setParameter("followingId", followingId);

        int liczba = (int) query.getSingleResult();

        return liczba > 0;
    }
}
