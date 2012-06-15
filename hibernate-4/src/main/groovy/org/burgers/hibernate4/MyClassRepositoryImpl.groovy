package org.burgers.hibernate4

import org.springframework.orm.hibernate3.HibernateTemplate
import org.springframework.stereotype.Repository
import org.springframework.beans.factory.annotation.Autowired
import org.hibernate.SessionFactory

@Repository
class MyClassRepositoryImpl implements MyClassRepository {
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
    }

    void delete(MyClass myClass) {
        hibernateTemplate.delete(myClass)
    }

    MyClass findById(Long id) {
        def myClass = new MyClass(id: id)
        hibernateTemplate.findByExample(myClass)[0]
    }

    List<MyClass> findAll() {
        hibernateTemplate.find("from MyClass")
    }

    void deleteAll() {
        hibernateTemplate.bulkUpdate("delete from MyClass")
    }

}
