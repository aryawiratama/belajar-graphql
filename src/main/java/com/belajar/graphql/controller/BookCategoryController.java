package com.belajar.graphql.controller;

import com.belajar.graphql.datafetcher.AllBookCategoryDataFetcher;
import com.belajar.graphql.datafetcher.BookCategoryFetcher;
import graphql.ExecutionResult;
import graphql.GraphQL;
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
@RequestMapping("/api/category")
public class BookCategoryController {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Value("classpath:BookCategory.graphqls")
    private Resource resource;

    private GraphQL graphQL;

    @Autowired
    private AllBookCategoryDataFetcher allBookCategory;
    @Autowired
    private BookCategoryFetcher bookCategoryFetcher;

    @PostConstruct
    public void loadSchema() throws IOException {
        File schemaFile = resource.getFile();
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", builder -> builder
                        .dataFetcher("allBookCategory", allBookCategory)
                        .dataFetcher("bookCategory", bookCategoryFetcher)
                )
                .build();
    }

    @PostMapping
    public ResponseEntity query(@RequestBody String query){
        ExecutionResult result = graphQL.execute(query);
        logger.info(String.valueOf(result.getErrors()));
        return ResponseEntity.ok(result.getData());
        //return ResponseEntity.ok("OKE" + query);
    }
}