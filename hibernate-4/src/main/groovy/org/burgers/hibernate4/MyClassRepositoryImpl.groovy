package org.burgers.hibernate4

import org.hibernate.Session
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class MyClassRepositoryImpl implements MyClassRepository {
    @Autowired
    SessionFactory sessionFactory

    void save(MyClass myClass) {
        Session session = sessionFactory.currentSession
        session.save(myClass)
    }

    void delete(MyClass myClass) {
        Session session = sessionFactory.currentSession
        session.delete(myClass)
    }

    MyClass findById(Long id) {
        Session session = sessionFactory.currentSession
        def query = session.createQuery("from MyClass where id = $id")
        query.list()[0]
    }

    MyClass findByName(String name) {
        Session session = sessionFactory.currentSession
        def query = session.createQuery("from MyClass where name = :name")
        query.setParameter("name", name)
        query.setCacheable(true)
        query.setCacheRegion("myClassRegion")
        query.list()[0]
    }

    List<MyClass> findAll() {
        Session session = sessionFactory.currentSession
        session.createQuery("from MyClass").list()
    }

    void deleteAll() {
        Session session = sessionFactory.currentSession
        session.createQuery("delete from MyClass").executeUpdate()
    }

}
