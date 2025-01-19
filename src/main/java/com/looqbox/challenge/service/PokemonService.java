package com.looqbox.challenge.service;

import com.looqbox.challenge.client.PokemonClient;
import com.looqbox.challenge.dto.PokemonDTO;
import com.looqbox.challenge.dto.PokemonResultsDTO;
import com.looqbox.challenge.dto.PokemonWithHighlightDTO;
import com.looqbox.challenge.sort.MergeSort;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.looqbox.challenge.sort.MergeSort.SortType;

@Service
public class PokemonService {

    private final PokemonClient pokemonClient;
    private final MergeSort mergeSort;

    public PokemonService(PokemonClient pokemonClient, MergeSort mergeSort) {
        this.pokemonClient = pokemonClient;
        this.mergeSort = mergeSort;
    }

    public PokemonResultsDTO getPokemons(String query, SortType sort) {
        List<String> pokemons = pokemonClient.getPokemonsFromApi();

        pokemons = filterPokemons(query, pokemons);
        pokemons = sortPokemons(sort, pokemons);

        return new PokemonResultsDTO(pokemons);
    }

    public PokemonWithHighlightDTO getPokemonsWithHighlight(String query, SortType sort) {
        List<String> pokemons = pokemonClient.getPokemonsFromApi();

        pokemons = filterPokemons(query, pokemons);
        pokemons = sortPokemons(sort, pokemons);

        return highlightPokemonNames(pokemons, query);
    }

    private List<String> filterPokemons(String query, List<String> pokemons) {
        if (StringUtils.isNotEmpty(query)) {
            pokemons = pokemons.stream()
                    .filter(name -> name.toLowerCase().contains(query.toLowerCase()))
                    .toList();
        }
        return pokemons;
    }

    private List<String> sortPokemons(SortType sort, List<String> pokemons) {
        return mergeSort.sort(pokemons, sort != null ? sort : SortType.ALPHABETICAL);
    }

    private PokemonWithHighlightDTO highlightPokemonNames(List<String> pokemonNames, String query) {
        if (StringUtils.isEmpty(query)) {
            List<PokemonDTO> result = pokemonNames.stream()
                    .map(PokemonDTO::new)
                    .toList();
            return new PokemonWithHighlightDTO(result);
        }

        List<PokemonDTO> result = pokemonNames.stream()
                .map(name -> {
                    String lowerCaseName = name.toLowerCase();
                    String lowerCaseQuery = query.toLowerCase();

                    if (lowerCaseName.contains(lowerCaseQuery)) {
                        String highlighted = name.replaceFirst("(?i)" + query, "<pre>" + query + "</pre>");
                        return new PokemonDTO(name, highlighted);
                    } else {
                        return new PokemonDTO(name, query);
                    }
                })
                .toList();

        return new PokemonWithHighlightDTO(result);
    }
}
