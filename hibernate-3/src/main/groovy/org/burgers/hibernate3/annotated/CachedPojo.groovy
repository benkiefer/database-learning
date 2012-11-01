package org.burgers.hibernate3.annotated

import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Column

@Entity
@Table(name = "tbtCachedPojo")
class CachedPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CachedPojoId")
    Long id

    @Column(name = "Name")
    String name
}
