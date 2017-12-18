package com.belajar.graphql.datafetcher;

import com.belajar.graphql.entity.Book;
import com.belajar.graphql.repository.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BookDataFetcher implements DataFetcher<Book>{

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book get(DataFetchingEnvironment env) {
        Map<String, Object> args = env.getArguments();
        return bookRepository.findOne(args.get("id").toString());
    }
}
