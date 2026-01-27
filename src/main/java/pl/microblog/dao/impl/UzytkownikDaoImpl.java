package pl.microblog.dao.impl;

import pl.microblog.dao.UzytkownikDao;
import pl.microblog.model.Uzytkownik;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
public class UzytkownikDaoImpl implements UzytkownikDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Uzytkownik znajdzPoLoginie(String login) {
        String queryString = "SELECT u FROM Uzytkownik u WHERE u.login = :login";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("login", login);

        List<Uzytkownik> wynik = query.getResultList();

        /*To się może zmienić przy pisaniu testów*/
        if (wynik.isEmpty()) {
            return null;
        }

        return wynik.get(0);
    }

    public void dodajUzytkownika(String login, String email, String haslo, LocalDateTime dataUtworzenia, String bio) {
        Uzytkownik uzytkownik = new Uzytkownik(0, login, email, haslo, dataUtworzenia, bio);

        entityManager.persist(uzytkownik);
    }

}
