package com.looqbox.challenge.model;

public class Pokemon {

    private String name;
    private String highlightedName;

    public Pokemon(String name, String highlightedName) {
        this.name = name;
        this.highlightedName = highlightedName;
    }

    public Pokemon(String name) {
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
