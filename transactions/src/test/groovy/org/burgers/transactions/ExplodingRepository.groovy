package org.burgers.transactions

import org.burgers.hibernate3.MyClassRepository
import org.burgers.hibernate3.MyClass

class ExplodingRepository implements MyClassRepository{
    MyClassRepository repository

    static boolean shouldExplode = false

    ExplodingRepository(MyClassRepository myClassRepository){
        this.repository = myClassRepository
    }

    void save(MyClass myClass) {
        repository.save(myClass)
        if (shouldExplode) throw new RuntimeException("Test Kaboom!!!")
    }

    void delete(MyClass myClass) {
    }

    MyClass findById(Long id) {
        null
    }

    List<MyClass> findAll() {
        null
    }

    void deleteAll() {
    }
}
