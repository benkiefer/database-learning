package org.burgers.database.learning.jdbc

import org.springframework.test.context.ContextConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.junit.Test
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.junit.runner.RunWith

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ["classpath:contexts/JdbcContext.xml"])
class SpringJdbcLearningTest {

    @Autowired
    JdbcTemplate jdbcTemplate

    @Test
    void simpleQuery() {
        assert 0 == recordCount()
        jdbcTemplate.execute("Insert into tbtMyClass(Name) values ('name')")
        assert 1 == recordCount()
        assert jdbcTemplate.queryForObject("select Name from tbtMyClass", String) == "name"
        cleanDatabase()
        assert recordCount() == 0
    }

    private int recordCount() {
        jdbcTemplate.queryForInt("Select count(*) from tbtMyClass")
    }

    private void cleanDatabase(){
        jdbcTemplate.execute("delete from tbtMyClass")
    }

}
