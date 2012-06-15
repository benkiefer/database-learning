package org.burgers.transactions

import org.burgers.hibernate3.MyClassRepository
import org.springframework.stereotype.Component
import org.burgers.hibernate3.MyClass
import org.springframework.transaction.annotation.Transactional
import org.springframework.beans.factory.annotation.Autowired

@Component
class MyServiceImpl implements MyService {

    @Autowired
    MyClassRepository myRepository

    @Transactional
    void doSomething() {
        myRepository.save(new MyClass(name: "MyName"))
    }
}
