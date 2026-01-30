package main.java.pl.microblog.dao.impl;

import main.java.pl.microblog.dao.WpisDao;
import main.java.pl.microblog.model.Wpis;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings("unchecked")
@Transactional
public class WpisDaoImpl implements WpisDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Wpis> wezTimelineUzytkownika(int userId) {
        String queryString = "SELECT w FROM Wpis w WHERE w.userID = :userId ORDER BY w.dataDodania DESC";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("userID", userId);

        return query.getResultList();
    }

    @Override
    public List<Wpis> wezFullTimelineUzytkownika(int userId) {
        String queryString = "SELECT DISTINCT w FROM Wpis w LEFT JOIN Follower f ON f.followingID = w.userID " +
                "AND f.followerID = :userId WHERE w.userID = :userId OR f.id IS NOT NULL ORDER BY w.dataDodania DESC";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("userId", userId);

        return query.getResultList();
    }

    @Override
    public List<Wpis> wezFullPublicTimeline() {
        String queryString = "SELECT w FROM Wpis w ORDER BY w.dataDodania DESC";
        Query query = entityManager.createQuery(queryString);

        return query.getResultList();
    }

    @Override
    public void dodajWpis(int userId, String tresc, LocalDateTime dataDodania, int liczbaLajkow) {
        Wpis wpis = new Wpis();
        wpis.setUserID(userId);
        wpis.setTresc(tresc);
        wpis.setDataDodania(dataDodania);
        wpis.setLiczbaLajkow(liczbaLajkow);

        entityManager.persist(wpis);
    }
}
