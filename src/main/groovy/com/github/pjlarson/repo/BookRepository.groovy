package com.github.pjlarson.repo

import com.github.pjlarson.domain.Book
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Created by Peter on 10/24/2015.
 */
public interface BookRepository  extends PagingAndSortingRepository<Book, Long>{
}
