package com.looqbox.challenge.dto;

import java.util.List;

public class PokemonWithHighlightDTO {

    private List<PokemonDTO> results;

    public PokemonWithHighlightDTO(List<PokemonDTO> results) {
        this.results = results;
    }

    public List<PokemonDTO> getResults() {
        return results;
    }
}
