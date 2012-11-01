package org.burgers.hibernate4

public interface MyClassRepository {
    void save(MyClass myClass)
    void delete(MyClass myClass)
    MyClass findById(Long id)
    MyClass findByName(String name)
    List<MyClass> findAll()
    void deleteAll()
}