package com.belajar.graphql.datafetcher;

import com.belajar.graphql.entity.BookCategory;
import com.belajar.graphql.repository.BookCategoryRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.StreamSupport;

@Component
public class AllBookCategoryDataFetcher implements DataFetcher<List<BookCategory>>{
    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @Override
    public List<BookCategory> get(DataFetchingEnvironment env) {
        List<BookCategory> bookCategories = new ArrayList<>();
        bookCategoryRepository.findAll().iterator().forEachRemaining(bookCategories::add);
        return bookCategories;
    }
}
