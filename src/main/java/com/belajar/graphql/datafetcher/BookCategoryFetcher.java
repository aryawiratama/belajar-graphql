package com.belajar.graphql.datafetcher;

import com.belajar.graphql.entity.BookCategory;
import com.belajar.graphql.repository.BookCategoryRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BookCategoryFetcher implements DataFetcher<BookCategory>{

    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @Override
    public BookCategory get(DataFetchingEnvironment env) {
        Map<String, Object> args = env.getArguments();
        return bookCategoryRepository.findOne(args.get("id").toString());
    }
}
