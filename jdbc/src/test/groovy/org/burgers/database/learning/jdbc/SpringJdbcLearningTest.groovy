package org.burgers.database.learning.jdbc

import org.springframework.test.context.ContextConfiguration
import org.springframework.beans.factory.annotation.Autowired
import javax.sql.DataSource
import org.springframework.jdbc.core.JdbcTemplate
import org.junit.Test
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.junit.runner.RunWith
import org.junit.Before
import java.sql.Connection
import java.sql.Statement
import java.sql.ResultSet
import org.junit.After

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ["classpath:contexts/DataAccessContext.xml"])
class SpringJdbcLearningTest {

    @Autowired
    JdbcTemplate jdbcTemplate

    @Before
    void setUp() {
        createDatabase()
    }

    @Test
    void simpleQuery() {
        assert 0 == recordCount()
        jdbcTemplate.execute("Insert into tbtMyThings(Id, Name) values (1, 'name')")
        assert 1 == recordCount()
        assert jdbcTemplate.queryForObject("select Name from tbtMyThings where Id = 1", String) == "name"
    }

    private int recordCount() {
        jdbcTemplate.queryForInt("Select count(*) from tbtMyThings")
    }

    void createDatabase() {
        jdbcTemplate.execute("CREATE TABLE tbtMyThings ( Id INTEGER, Name VARCHAR(20))")
    }

}
