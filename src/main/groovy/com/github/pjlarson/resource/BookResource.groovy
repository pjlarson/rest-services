package com.github.pjlarson.resource

import org.springframework.hateoas.ResourceSupport

/**
 * Created by Peter on 10/25/2015.
 */
class BookResource extends ResourceSupport {

    String bookName
    String applicationName

}
