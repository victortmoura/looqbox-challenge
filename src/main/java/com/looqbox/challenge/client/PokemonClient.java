package com.looqbox.challenge.client;

import com.looqbox.challenge.client.response.PokeApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class PokemonClient {

    @Value("${pokeapi.base-uri}")
    String apiUrl;

    private final RestTemplate restTemplate;

    public PokemonClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<String> getPokemonsFromApi() {
        PokeApiResponse pokeApiResponse = restTemplate.exchange(
                        apiUrl.concat("/pokemon"),
                        HttpMethod.GET,
                        null,
                        PokeApiResponse.class)
                .getBody();

        return parsePokemonResponse(pokeApiResponse);
    }

    private List<String> parsePokemonResponse(PokeApiResponse pokeApiResponse) {
        if (pokeApiResponse == null || pokeApiResponse.getResults() == null) {
            return List.of();
        }

        return pokeApiResponse.getResults()
                .stream()
                .map(pokemon -> pokemon.get("name"))
                .toList();
    }
}
