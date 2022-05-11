package com.kapmati.graphql.adapters.inbound.graphql;

import graphql.schema.DataFetcher;

import java.util.Map;

public interface DataFetcherGroup<T> {
    Map<String, DataFetcher<?>> getQueries();

    Map<String, DataFetcher<?>> getMutations();

    Map<String, DataFetcher<?>> getFieldQueries();

    Class<T> getType();
}
