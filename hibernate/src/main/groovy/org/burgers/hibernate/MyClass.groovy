package org.burgers.hibernate

import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.Column
import javax.annotation.Generated
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
