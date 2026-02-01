package WWSIS.Microblog.dao;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import WWSIS.Microblog.model.Wpis;
import WWSIS.Microblog.model.Uzytkownik;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext-test.xml"})
@Transactional
@Rollback(true)
  public class TestWpisDao {

    @Autowired
    WpisDao wpisDAO;

    @Autowired
    UzytkownikDao uzytkownikDAO;

    @Autowired
    FollowerDao followerDAO;

    private Uzytkownik userA;
    private Uzytkownik userB;

    @Before
    public void setUp() {
        uzytkownikDAO.dodajUzytkownika("userA", "a@test.pl", "pass", LocalDateTime.now().minusDays(3), "bio A");
        uzytkownikDAO.dodajUzytkownika("userB", "b@test.pl", "pass", LocalDateTime.now().minusDays(2), "bio B");

        userA = uzytkownikDAO.znajdzPoLoginie("userA");
        userB = uzytkownikDAO.znajdzPoLoginie("userB");

        assertNotNull(userA);
        assertNotNull(userB);
        assertTrue(userA.getId() > 0);
        assertTrue(userB.getId() > 0);
    }

    @Test
    public void testWezTimelineUzytkownikaZwracaPostyUzytkownika() {
        LocalDateTime t1 = LocalDateTime.now().minusMinutes(20);
        LocalDateTime t2 = LocalDateTime.now().minusMinutes(10);
        LocalDateTime t3 = LocalDateTime.now().minusMinutes(5);

        wpisDAO.dodajWpis(userA.getId(), "A-1", t1, 0);
        wpisDAO.dodajWpis(userB.getId(), "B-1", t2, 0);
        wpisDAO.dodajWpis(userA.getId(), "A-2", t3, 0);

        List<Wpis> aTimeline = wpisDAO.wezTimelineUzytkownika(userA.getId());
        assertNotNull(aTimeline);
        assertEquals(2, aTimeline.size());

        // wszystkie wpisy muszą należeć do userA
        for (Wpis w : aTimeline) {
            assertEquals(userA.getId(), w.getUserID());
        }

        // kolejność malejąca po dacie (A-2 przed A-1)
        assertTrue(aTimeline.get(0).getDataDodania().compareTo(aTimeline.get(1).getDataDodania()) >= 0);
    }

    @Test
    public void testWezFullTimelineUzytkownikaZwracaWlasneOrazFollowowane() {
        LocalDateTime t1 = LocalDateTime.now().minusMinutes(30);
        LocalDateTime t2 = LocalDateTime.now().minusMinutes(20);
        LocalDateTime t3 = LocalDateTime.now().minusMinutes(10);

        wpisDAO.dodajWpis(userA.getId(), "A-własny-wpis", t2, 0);
        wpisDAO.dodajWpis(userB.getId(), "B-stary-wpis", t1, 0);
        wpisDAO.dodajWpis(userB.getId(), "B-nowy-wpis", t3, 0);

        // A zaczyna followować B
        followerDAO.follow(userA.getId(), userB.getId());

        List<Wpis> fullA = wpisDAO.wezFullTimelineUzytkownika(userA.getId());
        assertNotNull(fullA);

        // co najmniej 3 wpisy
        assertTrue(fullA.size() >= 3);

        boolean maAwlasne = false;
        boolean maB = false;

        for (Wpis w : fullA) {
            if (w.getUserID() == userA.getId()) {
                maAwlasne = true;
            }
            if (w.getUserID() == userB.getId()) {
                maB = true;
            }
        }

        assertTrue("Powinien zawierać własny wpis userA", maAwlasne);
        assertTrue("Powinien zawierać wpisy userB, którego userA followuje", maB);

        // malejąco po dacie
        for (int i = 0; i < fullA.size() - 1; i++) {
            assertTrue(fullA.get(i).getDataDodania().compareTo(fullA.get(i + 1).getDataDodania()) >= 0);
        }
    }

    @Test
    public void testWezFullPublicTimelineZwracaMalejacoPoDacie() {
        LocalDateTime bazowyCzas = LocalDateTime.now();

        LocalDateTime t1 = bazowyCzas.minusMinutes(10);
        LocalDateTime t2 = bazowyCzas.minusMinutes(5);
        LocalDateTime t3 = bazowyCzas.minusMinutes(1);

        wpisDAO.dodajWpis(userA.getId(), "PUB_STARY", t1, 0);
        wpisDAO.dodajWpis(userA.getId(), "PUB_SREDNI", t2, 0);
        wpisDAO.dodajWpis(userA.getId(), "PUB_NOWY", t3, 0);

        List<Wpis> publicznyTimeLine = wpisDAO.wezFullPublicTimeline();
        assertNotNull(publicznyTimeLine);
        assertTrue("Publiczny timeline powinien mieć co najmniej 3 wpisy", publicznyTimeLine.size() >= 3);

        for (int i = 0; i < publicznyTimeLine.size() - 1; i++) {
            assertTrue("Publiczny timeline powinien być posortowany malejąco po dacie",
                    publicznyTimeLine.get(i).getDataDodania().compareTo(publicznyTimeLine.get(i + 1).getDataDodania()) >= 0);
        }
    }


    @Test
    public void testDodajWpisZapisujeWpis() {
        LocalDateTime base = LocalDateTime.now();
        LocalDateTime t = base.minusMinutes(2);

        wpisDAO.dodajWpis(userA.getId(), "TEST_DODAJ_WPIS", t, 7);

        List<Wpis> publicznyTimeLine = wpisDAO.wezFullPublicTimeline();
        assertNotNull(publicznyTimeLine);

        boolean znaleziony = false;
        for (Wpis w : publicznyTimeLine) {
            if (w.getUserID() == userA.getId()
                    && "TEST_DODAJ_WPIS".equals(w.getTresc())
                    && w.getLiczbaLajkow() == 7
                    && t.equals(w.getDataDodania())) {
                znaleziony = true;
                break;
            }
        }

        assertTrue("Dodany wpis powinien być widoczny w bazie (public timeline)", znaleziony);
    }
}
