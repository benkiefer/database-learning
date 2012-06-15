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
        Session session = sessionFactory.openSession()
        session.beginTransaction()
        session.save(myClass)
        session.getTransaction().commit()
        session.close()
    }

    void delete(MyClass myClass) {
        Session session = sessionFactory.openSession()
        session.beginTransaction()
        session.delete(myClass)
        session.getTransaction().commit()
        session.close()
    }

    MyClass findById(Long id) {
        def result
        Session session = sessionFactory.openSession()
        result = session.createQuery("from MyClass where id = $id").list()[0]
        session.close()
        result
    }

    List<MyClass> findAll() {
        def result
        Session session = sessionFactory.openSession()
        result = session.createQuery("from MyClass").list()
        session.close()
        result
    }

    void deleteAll() {
        Session session = sessionFactory.openSession()
        session.beginTransaction()
        session.createQuery("delete from MyClass").executeUpdate()
        session.getTransaction().commit()
        session.close()
    }

}
