package org.burgers.hibernate4

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class MyServiceImpl implements MyService {

    @Autowired
    MyClassRepository myRepository

    @Transactional
    void doSomething() {
        myRepository.save(new MyClass(name: "MyName"))
    }

}
