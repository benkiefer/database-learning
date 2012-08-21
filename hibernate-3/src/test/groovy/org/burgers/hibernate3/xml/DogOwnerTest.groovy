package org.burgers.hibernate3.xml

import org.hibernate.SessionFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.orm.hibernate3.HibernateTemplate
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.transaction.annotation.Transactional

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ["classpath:contexts/HibernateContext.xml"])
class DogOwnerTest {
    @Autowired SessionFactory sessionFactory
    HibernateTemplate hibernateTemplate
    Owner owner
    Dog dog

    @Before
    void setUp(){
        hibernateTemplate = new HibernateTemplate(sessionFactory)
        hibernateTemplate.bulkUpdate("delete from Dog")
        hibernateTemplate.bulkUpdate("delete from Owner")
        owner = new Owner(name: "Timmy")
        dog = new Dog(name: "Lassie")
    }

    @Test
    void saveDog(){
        hibernateTemplate.save(dog)
        assert hibernateTemplate.find("from Dog").size() == 1
    }

    @Test
    void saveOwner(){
        hibernateTemplate.save(owner)
        assert hibernateTemplate.find("from Owner").size() == 1
    }

    @Test
    void ownerWithDog(){
        owner.dogs = [dog] as Set
        hibernateTemplate.save(owner)
        Owner topOwner = hibernateTemplate.find("from Owner")[0]
        assert topOwner.dogs.toList()[0].name == "Lassie"
    }




}
