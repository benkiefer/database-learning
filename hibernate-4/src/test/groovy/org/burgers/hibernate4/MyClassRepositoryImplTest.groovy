package org.burgers.hibernate4

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.transaction.annotation.Transactional

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ["classpath:contexts/HibernateContext.xml"])
@Transactional
class MyClassRepositoryImplTest {

    @Autowired
    MyClassRepository myRepository

    @Before
    void setUp() {
        myRepository.deleteAll()
    }

    @Test
    void save_delete() {
        def mine = new MyClass(name: "bob")
        myRepository.save(mine)
        def results = myRepository.findAll()
        assert results.size() == 1
        assert results[0].name == "bob"
        myRepository.delete(mine)
        assert myRepository.findAll().isEmpty()
    }

    @Test
    void findAll_deleteAll() {
        def bob = new MyClass(name: "bob")
        def larry = new MyClass(name: "larry")
        myRepository.save(bob)
        myRepository.save(larry)
        def results = myRepository.findAll()
        assert results.size() == 2
        assert results.find {it.name == "bob"}
        assert results.find {it.name == "larry"}
        myRepository.deleteAll()
        assert myRepository.findAll().isEmpty()
    }

    @Test
    void findById() {
        def bob = new MyClass(name: "bob")
        myRepository.save(bob)
        def id = myRepository.findAll()[0].id
        assert myRepository.findById(id).name == "bob"
    }

}
