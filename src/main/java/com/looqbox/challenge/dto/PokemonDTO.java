package com.looqbox.challenge.dto;

public class PokemonDTO {

    private String name;
    private String highlightedName;

    public PokemonDTO(String name, String highlightedName) {
        this.name = name;
        this.highlightedName = highlightedName;
    }

    public PokemonDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHighlightedName() {
        return highlightedName;
    }

    public void setHighlightedName(String highlightedName) {
        this.highlightedName = highlightedName;
    }
}
