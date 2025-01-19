package com.looqbox.challenge.client.response;

import java.util.LinkedHashMap;
import java.util.List;

public class PokeApiResponse {

    private List<LinkedHashMap<String, String>> results;

    public PokeApiResponse() {
    }

    public List<LinkedHashMap<String, String>> getResults() {
        return results;
    }

    public void setResults(List<LinkedHashMap<String, String>> results) {
        this.results = results;
    }
}
