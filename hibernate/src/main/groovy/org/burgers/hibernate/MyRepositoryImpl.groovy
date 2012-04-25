package org.burgers.hibernate

import org.springframework.orm.hibernate3.HibernateTemplate
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired
import org.hibernate.SessionFactory

@Transactional
@Component
class MyRepositoryImpl implements MyRepository {
    SessionFactory sessionFactory
    HibernateTemplate hibernateTemplate

    @Autowired
    void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory
        this.hibernateTemplate = new HibernateTemplate(sessionFactory)
    }

    void save(MyClass myClass) {
        hibernateTemplate.saveOrUpdate(myClass)
        hibernateTemplate.flush()
        sessionFactory.close()
//        hibernateTemplate.
    }

    void delete(MyClass myClass) {
        hibernateTemplate.delete(myClass)
    }

    void findUserById(Long id) {
        def myClass = new MyClass(id: id)
        hibernateTemplate.findByExample(myClass)[0]
    }

    List<MyClass> findAllMyClass() {
        hibernateTemplate.find("from MyClass")
    }

}
