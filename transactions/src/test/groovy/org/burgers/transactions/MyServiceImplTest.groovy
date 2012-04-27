package org.burgers.transactions

import org.junit.Before

import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.ContextConfiguration
import org.springframework.beans.factory.annotation.Autowired

import org.burgers.hibernate.MyClassRepository
import org.junit.Test

import static junit.framework.Assert.fail
import org.junit.After

@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration(locations=["classpath:contexts/TransactionContext.xml"])
class MyServiceImplTest {
    @Autowired
    MyService myService

    @Autowired
    MyClassRepository myRepository

    @Before
    void setUp() {
        myRepository.deleteAll()
        assert myRepository.findAll().isEmpty()
    }

    @Test
    void doSomething_happy_path(){
        myService.doSomething()
        def results = myRepository.findAll()
        assert results.size() == 1
        assert results[0].name == "MyName"
    }

    @Test
    void doSomething_fail_and_roll_back(){
        myService.myRepository = new ExplodingRepository(myRepository)
        ExplodingRepository.shouldExplode = true

        try{
            myService.doSomething()
            fail("Should not get here")
        } catch (e){
            assert myRepository.findAll().size() == 0
        }
    }

    @After
    void tearDown(){
        ExplodingRepository.shouldExplode = false
    }

}
