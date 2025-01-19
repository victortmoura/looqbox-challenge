package com.looqbox.challenge.controller;

import com.looqbox.challenge.dto.PokemonResultsDTO;
import com.looqbox.challenge.dto.PokemonWithHighlightDTO;
import com.looqbox.challenge.service.PokemonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.looqbox.challenge.sort.MergeSort.SortType;

@RestController
@RequestMapping("pokemons")
public class PokemonController {

    private PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public ResponseEntity<PokemonResultsDTO> getPokemons(@RequestParam(required = false) String query,
                                                         @RequestParam(required = false, defaultValue = "ALPHABETICAL") SortType sort) {
        return ResponseEntity.ok(pokemonService.getPokemons(query, sort));
    }

    @GetMapping("/highlight")
    public ResponseEntity<PokemonWithHighlightDTO> getPokemonsWithHighlight(@RequestParam(required = false) String query,
                                                                            @RequestParam(required = false, defaultValue = "ALPHABETICAL") SortType sort) {
        return ResponseEntity.ok(pokemonService.getPokemonsWithHighlight(query, sort));
    }

}
