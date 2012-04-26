package org.burgers.hibernate

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.ContextConfiguration
import org.springframework.beans.factory.annotation.Autowired

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ["classpath:contexts/HibernateContext.xml"])
class MyRepositoryImplTest {

    @Autowired
    MyRepository myRepository

    @Test
    void query() {
        def mine = new MyClass(name: "bob")
        myRepository.save(mine)
        def results = myRepository.findAllMyClass()
        assert results.size() == 1
        assert results[0].name == "bob"
        myRepository.delete(mine)
        assert myRepository.findAllMyClass().isEmpty()
    }

}
