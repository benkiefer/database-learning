package org.burgers.hibernate4

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import javax.sql.DataSource

import static junit.framework.Assert.fail
import org.hibernate.SessionFactory

@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration(locations = ["classpath:contexts/HibernateContext.xml"])
class MyServiceImplTest {
    @Autowired
    MyService myService

    @Autowired
    MyClassRepository myRepository

    @Autowired
    DataSource dataSource

    JdbcTemplate jdbcTemplate

    @Autowired
    SessionFactory sessionFactory

    @Before
    void setUp() {
        jdbcTemplate = new JdbcTemplate(dataSource)
        jdbcTemplate.execute("delete from tbtMyClass")
        assert getMyClassCount() == 0
    }

    int getMyClassCount(){
        jdbcTemplate.queryForInt("select count(*) from tbtMyClass")
    }

    @Test
    void doSomething_happy_path(){
        myService.doSomething()
        assert getMyClassCount() == 1
    }

    @Test
    void caching(){
        // save the record
        myService.doSomething()

        myService.findByName("MyName")

        assert getQueryCache().putCount == 1
        assert getQueryCache().hitCount == 0

        myService.findByName("MyName")

        assert getQueryCache().putCount == 1
        assert getQueryCache().hitCount == 1

        /*
       IMPORTANT:
       Queried entities are not put into the second-level cache unless you close the session.
       Even if you requery with the very same objects, still no caching will happen.

       That's why I put the tests here, because each query of the service will hit the findByName method and open/close a new session.
        */
    }

    @Test
    void doSomething_fail_and_roll_back(){
        myService.myRepository = new ExplodingRepository(myRepository)
        ExplodingRepository.shouldExplode = true

        try{
            myService.doSomething()
            fail("Should not get here")
        } catch (e){
            assert getMyClassCount() == 0
        }
    }

    private getQueryCache() {
        sessionFactory.getStatistics().getSecondLevelCacheStatistics("myClassRegion")
    }

    @After
    void tearDown(){
        ExplodingRepository.shouldExplode = false
    }

}
