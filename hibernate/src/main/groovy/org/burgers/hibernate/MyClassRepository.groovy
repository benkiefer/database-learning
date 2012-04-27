package org.burgers.hibernate

public interface MyClassRepository {
    void save(MyClass myClass)
    void delete(MyClass myClass)
    MyClass findById(Long id)
    List<MyClass> findAll()
    void deleteAll()
}