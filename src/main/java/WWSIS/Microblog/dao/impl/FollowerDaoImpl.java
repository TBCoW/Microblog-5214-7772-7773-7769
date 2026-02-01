package WWSIS.Microblog.dao.impl;

import WWSIS.Microblog.dao.FollowerDao;
import WWSIS.Microblog.model.Follower;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.time.LocalDate;

@SuppressWarnings("unchecked")
@Transactional
public class FollowerDaoImpl implements FollowerDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            throw new IllegalArgumentException("Nie można obserwować samego siebie");
        }
        Follower f = new Follower();
        f.setFollowerID(followerId);
        f.setFolloweeID(followeeId);
        f.setDataObserwacji(LocalDate.now());
        entityManager.persist(f);
    }

    @Override
    public void unfollow(int followerId, int followeeId) {
        String queryString = "DELETE FROM Follower f WHERE f.followerID = :followerId AND f.followeeID = :followeeId";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("followerId", followerId);
        query.setParameter("followeeId", followeeId);

        query.executeUpdate();
    }

    @Override
    public boolean czyFollowuje(int followerId, int followeeId) {
        String queryString = "SELECT COUNT(f) FROM Follower f WHERE f.followerID = :followerId AND f.followeeID = :followeeId";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("followerId", followerId);
        query.setParameter("followeeId", followeeId);

        Long liczba = (Long) query.getSingleResult();

        return liczba > 0;
    }
}
