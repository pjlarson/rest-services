package com.github.pjlarson.domain

import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass

/**
 * Created by Peter on 10/25/2015.
 */
@MappedSuperclass
class AbstractEntity {

    @Id
    @GeneratedValue
    Long id
}
