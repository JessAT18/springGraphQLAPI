package com.jessat18.springgraphqlapi.graphql;

import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class GraphQLDataFetchers {
    private List<User> users = Arrays.asList(
            new User(1, "Mitchell", "Carpentier", "mcarpentier@jessat18.com"),
            new User(2, "Emily", "O'Brien", "eobrien@jessat18.com"),
            new User(3, "Leonard", "Fontaine", "lfontaine@jessat18.com")
    );

    public DataFetcher<List<User>> getFindAllDataFetcher() {
        return dataFetchingEnvironment -> this.users;
    }
}

class User {
    private Integer id;
    private String name;
    private String surname;
    private String email;

    public User(Integer id, String name, String surname, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}