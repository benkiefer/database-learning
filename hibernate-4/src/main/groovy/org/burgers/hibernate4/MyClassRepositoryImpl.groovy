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
        def result
        Session session = sessionFactory.currentSession
        session.createQuery("from MyClass where id = $id").list()[0]
    }

    List<MyClass> findAll() {
        def result
        Session session = sessionFactory.currentSession
        session.createQuery("from MyClass").list()
    }

    void deleteAll() {
        Session session = sessionFactory.currentSession
        session.createQuery("delete from MyClass").executeUpdate()
    }

}
