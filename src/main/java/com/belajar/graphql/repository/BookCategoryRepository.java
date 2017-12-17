package com.belajar.graphql.repository;

import com.belajar.graphql.entity.BookCategory;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookCategoryRepository extends PagingAndSortingRepository<BookCategory,String> {
}
