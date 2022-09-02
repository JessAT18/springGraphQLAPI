package com.jessat18.springgraphqlapi.graphql;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.errors.SchemaProblem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class GraphQLProvider {
    @Value("classpath:schema.graphqls")
    private Resource resource;
    private GraphQL graphQL;
    @PostConstruct
    public void init() throws SchemaProblem, IOException {
        GraphQLSchema graphQLSchema = this.buildSchema();
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private GraphQLSchema buildSchema() throws SchemaProblem, IOException {
        final TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(resource.getInputStream());
        SchemaGenerator schemaGenerator = new SchemaGenerator();

        return schemaGenerator.makeExecutableSchema(typeRegistry, RuntimeWiring.newRuntimeWiring().build());
    }

    @Bean
    public GraphQL getGraphQL() {
        return this.graphQL;
    }
}
