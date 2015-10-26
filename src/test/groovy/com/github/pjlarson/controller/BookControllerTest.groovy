package com.github.pjlarson.controller

import com.github.pjlarson.RestServicesApplication
import com.github.pjlarson.domain.Book
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.TestRestTemplate
import org.springframework.http.ResponseEntity
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.web.client.RestTemplate

import javax.xml.ws.Response

/**
 * Created by Peter on 10/24/2015.
 */

@SpringApplicationConfiguration(classes = RestServicesApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port=9000")
class BookControllerTest extends spock.lang.Specification {


    RestTemplate restTemplate = new TestRestTemplate()


    def "Test item GET"() {

        when:
        ResponseEntity<Book> entity = restTemplate.getForEntity("http://localhost:9000/api/book", Book.class)

        then:
        entity.statusCode.is2xxSuccessful() == true
        entity.body.status == 'Hello'

    }

    def "saves a new book"(){

        given:
        def location = 'http://localhost:9000/api/book'
        def newBook = new Book(application: 'RIMMS', bookName: '1234')

        when:
        URI uri = restTemplate.postForLocation(location, newBook)

        ResponseEntity<Book> responseEntity = restTemplate.getForEntity(uri, Book.class)
        Book book = responseEntity.getBody()

        then:
        book.application == 'RIMMS'
        book.bookName == '1234'

    }

}
