package com.github.pjlarson.domain

import javax.persistence.Entity

/**
 * Created by Peter on 10/24/2015.
 */
@Entity
class Book extends AbstractEntity {

    String bookName
    String application

}
