package com.belajar.graphql.repository;

import com.belajar.graphql.entity.Book;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends PagingAndSortingRepository<Book, String> {
}
