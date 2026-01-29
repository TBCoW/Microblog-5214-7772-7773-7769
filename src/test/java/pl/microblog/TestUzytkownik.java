package pl.microblog.dao;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(location={"classpath:applicationContext-test.xml"})
@Transactional
@Rollback(true)
  public class TestUzytkownikDao {

   @Autowired
    UzytkownikDao uzytkownikDao;

    UzytkownikDao uzytkownikDao;
