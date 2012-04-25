package org.burgers.hibernate

public interface MyRepository {
    void save(MyClass myClass)
    void delete(MyClass myClass)
    void findUserById(Long id)
    List<MyClass> findAllMyClass()
}