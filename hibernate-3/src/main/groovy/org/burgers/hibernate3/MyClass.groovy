package org.burgers.hibernate3

import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.Column
import javax.persistence.GenerationType
import javax.persistence.GeneratedValue

@Entity
@Table(name = "tbtMyClass")
class MyClass implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MyClassId")
    Long id

    @Column(name = "Name")
    String name
}
