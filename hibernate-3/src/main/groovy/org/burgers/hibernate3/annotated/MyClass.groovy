package org.burgers.hibernate3.annotated

import javax.persistence.*

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
