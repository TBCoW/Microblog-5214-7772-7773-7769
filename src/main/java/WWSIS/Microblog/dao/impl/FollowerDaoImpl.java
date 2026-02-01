package WWSIS.Microblog.dao.impl;

import WWSIS.Microblog.dao.FollowerDao;
import WWSIS.Microblog.model.Follower;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@SuppressWarnings("unchecked")
@Transactional
public class FollowerDaoImpl implements FollowerDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void follow(int followerId, int followeeId) {
        Follower f = new Follower();
        f.setFollowerID(followerId);
        f.setFollowingID(followeeId);

        entityManager.persist(f);
    }

    @Override
    public void unfollow(int followerId, int followeeId) {
        String queryString = "DELETE FROM Follower f WHERE f.followerID = :followerId AND f.followingID = :followingId";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("followerId", followerId);
        query.setParameter("followingId", followeeId);

        query.executeUpdate();
    }

    @Override
    public boolean czyFollowuje(int followerId, int followeeId) {
        String queryString = "SELECT COUNT(f) FROM Follower f WHERE f.followerID = :followerId AND f.followingID = :followingId";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("followerId", followerId);
        query.setParameter("followingId", followeeId);

        Long liczba = (Long) query.getSingleResult();

        return liczba > 0;
    }
}
