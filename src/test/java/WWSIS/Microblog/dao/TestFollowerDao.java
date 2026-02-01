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
import WWSIS.Microblog.model.Uzytkownik;

import java.time.LocalDateTime;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext-test.xml"})
@Transactional
@Rollback(true)
public class TestFollowerDao {

    @Autowired
    FollowerDao followerDAO;

    @Autowired
    UzytkownikDao uzytkownikDAO;

    Uzytkownik newFollowee;
    Uzytkownik newFollower;

    @Before
    public void setUp() {
        uzytkownikDAO.dodajUzytkownika("followee_login", "followee@test.pl", "pass",
                LocalDateTime.now().minusDays(2), "bio followee");
        uzytkownikDAO.dodajUzytkownika("follower_login", "follower@test.pl", "pass",
                LocalDateTime.now().minusDays(1), "bio follower");

        newFollowee = uzytkownikDAO.znajdzPoLoginie("followee_login");
        newFollower = uzytkownikDAO.znajdzPoLoginie("follower_login");

        assertNotNull(newFollowee);
        assertNotNull(newFollower);
        assertTrue("ID followee powinno być > 0", newFollowee.getId() > 0);
        assertTrue("ID follower powinno być > 0", newFollower.getId() > 0);
    }

    @Test
    public void testAddFollowerDodajeRelacje() {
        assertFalse(followerDAO.czyFollowuje(newFollower.getId(), newFollowee.getId()));

        followerDAO.follow(newFollower.getId(), newFollowee.getId());

        assertTrue(followerDAO.czyFollowuje(newFollower.getId(), newFollowee.getId()));
    }

    @Test
    public void testUnfollowUsuwaRelacje() {
        followerDAO.follow(newFollower.getId(), newFollowee.getId());
        assertTrue(followerDAO.czyFollowuje(newFollower.getId(), newFollowee.getId()));

        followerDAO.unfollow(newFollower.getId(), newFollowee.getId());
        assertFalse(followerDAO.czyFollowuje(newFollower.getId(), newFollowee.getId()));
    }

    @Test
    public void testCzyFollowujeFalseGdyBrakRelacji() {
        assertFalse(followerDAO.czyFollowuje(newFollower.getId(), newFollowee.getId()));
    }
}