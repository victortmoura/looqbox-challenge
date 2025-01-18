package com.looqbox.challenge.client;

import com.looqbox.challenge.model.PokeApiResponse;
import com.looqbox.challenge.model.Pokemon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PokemonClient {

    @Value("${pokeapi.base-uri}")
    String apiUrl;

    private final RestTemplate restTemplate;

    public PokemonClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Pokemon> getPokemonsFromApi() {
        PokeApiResponse pokeApiResponse = restTemplate.exchange(
                        apiUrl.concat("/pokemon"),
                        HttpMethod.GET,
                        null,
                        PokeApiResponse.class)
                .getBody();

        return parsePokemonResponse(pokeApiResponse);
    }

    private List<Pokemon> parsePokemonResponse(PokeApiResponse pokeApiResponse) {
        if (pokeApiResponse == null || pokeApiResponse.getResults() == null) {
            return List.of();
        }

        return pokeApiResponse.getResults()
                .stream()
                .map(pokemon -> new Pokemon(pokemon.get("name")))
                .collect(Collectors.toList());
    }
}
