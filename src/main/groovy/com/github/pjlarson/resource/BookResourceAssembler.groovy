package com.github.pjlarson.resource

import com.github.pjlarson.controller.BookController
import com.github.pjlarson.domain.Book
import org.springframework.hateoas.mvc.ResourceAssemblerSupport

/**
 * Created by Peter on 10/25/2015.
 */
class BookResourceAssembler  extends ResourceAssemblerSupport<Book, BookResource> {

    BookResourceAssembler() {
        super(BookController, BookResource)
    }

    @Override
    BookResource toResource(Book book) {

        BookResource resource = createResourceWithId(book.bookName, book)
        resource.applicationName = book.application
        resource.bookName = book.bookName
        return resource
    }
}
