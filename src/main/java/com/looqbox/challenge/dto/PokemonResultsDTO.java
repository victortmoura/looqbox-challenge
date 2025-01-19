package com.looqbox.challenge.dto;

import java.util.List;

public class PokemonResultsDTO {

    private List<String> results;

    public PokemonResultsDTO(List<String> results) {
        this.results = results;
    }

    public List<String> getResults() {
        return results;
    }
}
