package WWSIS.Microblog.dao;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.microblog.dao.UzytkownikDao;
import pl.microblog.dao.Uzytkownik;
import WWSIS.Microblog.model.Uzytkownik;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(location={"classpath:applicationContext-test.xml"})
@Transactional
@Rollback(true)
  public class TestUzytkownikDao {

   @Autowired
    UzytkownikDao uzytkownikDao;

    UzytkownikDao uzytkownikDao;

     @Test
    public void dodajUzytkownika_iZnajdzPoLoginie() {
        LocalDateTime teraz = LocalDateTime.now();

        uzytkownikDao.dodajUzytkownika(
                "test_login",
                "test@test.pl",
                "haslo",
                teraz,
                "bio test"
        );

        Uzytkownik u = uzytkownikDao.znajdzPoLoginie("test_login");

        assertNotNull(u);
        assertEquals("test_login", u.getLogin());
        assertEquals("test@test.pl", u.getEmail());
        assertEquals("bio test", u.getBio());
    }

    @Test
    public void znajdzPoLoginie_gdyNieIstnieje() {
        Uzytkownik u = uzytkownikDao.znajdzPoLoginie("nie_istnieje");
        assertNull(u);
    }
}
