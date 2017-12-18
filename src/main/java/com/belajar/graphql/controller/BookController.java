package com.belajar.graphql.controller;

import com.belajar.graphql.datafetcher.BookCategoryFetcher;
import com.belajar.graphql.datafetcher.BookDataFetcher;
import com.belajar.graphql.datafetcher.BooksDataFetcher;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/book")
public class BookController {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Value("classpath:Book.graphqls")
    private Resource resource;

    private GraphQL graphQL;

    @Autowired
    private BooksDataFetcher booksDataFetcher;
    @Autowired
    private BookDataFetcher bookDataFetcher;
    @Autowired
    private BookCategoryFetcher bookCategoryFetcher;

    @PostConstruct
    public void loadSchema() throws IOException {
        File file = resource.getFile();
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(file);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring
                .newRuntimeWiring()
                .type("Query", builder ->  builder
                        .dataFetcher("books", booksDataFetcher)
                        .dataFetcher("book", bookDataFetcher)
                )
                .type("Book", builder -> builder.dataFetcher("category", bookCategoryFetcher))
                .build();
    }

    @PostMapping("/query")
    public ResponseEntity query(@RequestBody String query){
        ExecutionResult result = graphQL.execute(query);
        logger.info(result.getErrors().toString());
        return ResponseEntity.ok(result.getData());
    }
}
