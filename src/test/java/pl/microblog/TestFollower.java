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
import pl.microblog.dao.FollowerDao;
import pl.microblog.dao.Follower;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(location={"classpath:applicationContext-test.xml"})
@Transactional
@Rollback(true)
  public class TestFollowerDao {

   @Autowired
    FollowerDao followerDao;

    FollowerDao followerDao;
