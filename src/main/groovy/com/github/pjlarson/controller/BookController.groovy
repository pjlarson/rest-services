package com.github.pjlarson.controller

import com.github.pjlarson.domain.Book
import com.github.pjlarson.repo.BookRepository
import com.github.pjlarson.resource.BookResource
import com.github.pjlarson.resource.BookResourceAssembler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.hateoas.EntityLinks
import org.springframework.hateoas.ExposesResourceFor
import org.springframework.hateoas.Resources
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriInfo


/**
 * Created by Peter on 10/24/2015.
 */
@Profile('hateoas')
@Component
@Path("/book")
@Produces(MediaType.APPLICATION_JSON)
@ExposesResourceFor(Book.class)
class BookController {

    @Autowired
    private BookRepository bookRepository

    @Autowired EntityLinks entityLinks

    @Context
    UriInfo uriInfo

    @PostConstruct
    def init(){
        Book book = new Book();
        book.application = 'Murex'
        book.bookName = 'ABC'
        bookRepository.save(book)
    }

//    @GET
//    public Iterable<Book> findAll(){
//        return bookRepository.findAll()
//    }

    @GET
    public Response findAllResources(){
        Iterable<Book> books = bookRepository.findAll()

        BookResourceAssembler assembler = new BookResourceAssembler()
        List<BookResource> resources = assembler.toResources(books)

        Resources<BookResource> wrapped = new Resources<>(resources)
        wrapped.add(
                JaxRsLinkBuilder
                .linkTo(BookController.class)
                .withSelfRel()
        )

        return Response.ok(wrapped).build()
    }

    @GET
    @Path("{id}")
    public Book findOne(@PathParam('id')Long id){
        return bookRepository.findOne(id)
    }

    @DELETE
    @Path('{id}')
    public Response delete (@PathParam('id')Long id){
        bookRepository.delete(id)
        return Response.accepted().build()
    }

    @POST
    Response save(Book book){
        book = bookRepository.save(book)

        URI location = uriInfo.getAbsolutePathBuilder()
            .path('{id}')
            .resolveTemplate('id', book.id)
            .build()

        return Response.created(location).build()

    }

}
