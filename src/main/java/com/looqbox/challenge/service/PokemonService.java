package com.looqbox.challenge.service;

import com.looqbox.challenge.client.PokemonClient;
import com.looqbox.challenge.model.Pokemon;
import com.looqbox.challenge.model.PokemonResponse;
import com.looqbox.challenge.sort.MergeSort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.looqbox.challenge.sort.MergeSort.SortType;

@Service
public class PokemonService {

    private final PokemonClient pokemonClient;
    private final MergeSort mergeSort;

    public PokemonService(PokemonClient pokemonClient, MergeSort mergeSort) {
        this.pokemonClient = pokemonClient;
        this.mergeSort = mergeSort;
    }

    public PokemonResponse getPokemons(String query, SortType sort) {
        List<Pokemon> pokemons = pokemonClient.getPokemonsFromApi();

        if (query != null && !query.isEmpty()) {
            pokemons = filterPokemonsByQuery(pokemons, query);
        }

        List<String> result = pokemons.stream().map(Pokemon::getName).toList();
        List<String> sortedPokemons = mergeSort.sort(result, sort != null ? sort : SortType.ALPHABETICAL);

        return new PokemonResponse(sortedPokemons);
    }

    private List<Pokemon> filterPokemonsByQuery(List<Pokemon> pokemons, String query) {
        return pokemons.stream()
                .filter(pokemon -> pokemon.getName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}
