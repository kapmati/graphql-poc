package com.kapmati.graphql.adapters.inbound.graphql;

import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.stereotype.Component;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Component
public class GraphQLSchemaProvider {
    private final AuthorDataFetchers authorDataFetchers;
    private final BookDataFetchers bookDataFetchers;

    public GraphQLSchemaProvider(AuthorDataFetchers authorDataFetchers,
                                 BookDataFetchers bookDataFetchers) {
        this.authorDataFetchers = authorDataFetchers;
        this.bookDataFetchers = bookDataFetchers;
    }

    public GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Mutation")
                        .dataFetcher("addBook", bookDataFetchers.addBookDataFetcher())
                        .dataFetcher("addAuthor", authorDataFetchers.addAuthorDataFetcher()))
                .type(newTypeWiring("Query")
                        .dataFetcher("books", bookDataFetchers.getBooksDataFetcher())
                        .dataFetcher("bookById", bookDataFetchers.getBookByIdDataFetcher())
                        .dataFetcher("authorById", authorDataFetchers.getAuthorByIdDataFetcher()))
                .type(newTypeWiring("Book")
                        .dataFetcher("author", authorDataFetchers.getAuthorDataFetcher())
                        .dataFetcher("pageCount", bookDataFetchers.getPageCountDataFetcher()))
                .build();
    }
}
