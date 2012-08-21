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

    @After
    void tearDown(){
        ExplodingRepository.shouldExplode = false
    }

}
