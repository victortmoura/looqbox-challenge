package com.looqbox.challenge.model;

import java.util.List;

public class PokemonResponse {

    private List<String> results;

    public PokemonResponse(List<String> results) {
        this.results = results;
    }

    public List<String> getResults() {
        return results;
    }
}
