package org.burgers.hibernate3.annotated

import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.orm.hibernate3.HibernateTemplate
import org.springframework.stereotype.Repository

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
