package com.looqbox.challenge.model;

import java.util.List;

public class PokemonWithHighlightResponse {

    private List<Pokemon> results;

    public PokemonWithHighlightResponse(List<Pokemon> results) {
        this.results = results;
    }

    public List<Pokemon> getResults() {
        return results;
    }
}
