package com.looqbox.challenge.client;

import com.looqbox.challenge.client.response.PokeApiResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

class PokemonClientTest {

    @Mock
    private RestTemplate restTemplate;

    private PokemonClient pokemonClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pokemonClient = new PokemonClient(restTemplate);
        pokemonClient.apiUrl = "https://pokeapi.co/api/v2";
    }

    @Test
    @DisplayName("Get pokemons from API should return list of pokemons names when successful")
    void getPokemonsFromApi_shouldReturnListOfPokemonNames_whenSuccessful() {
        PokeApiResponse mockResponse = new PokeApiResponse();

        LinkedHashMap<String, String> pokemon1 = new LinkedHashMap<>();
        pokemon1.put("name", "pikachu");
        pokemon1.put("url", "https://pokeapi.co/api/v2/pokemon/25");

        LinkedHashMap<String, String> pokemon2 = new LinkedHashMap<>();
        pokemon2.put("name", "charmander");
        pokemon2.put("url", "https://pokeapi.co/api/v2/pokemon/4");

        mockResponse.setResults(List.of(pokemon1, pokemon2));

        when(restTemplate.exchange(
                eq("https://pokeapi.co/api/v2/pokemon"),
                eq(HttpMethod.GET),
                any(),
                eq(PokeApiResponse.class))
        ).thenReturn(ResponseEntity.ok(mockResponse));

        List<String> result = pokemonClient.getPokemonsFromApi();

        Assertions.assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .hasSize(2);
        Assertions.assertThat(result.get(0)).isEqualTo("pikachu");
        Assertions.assertThat(result.get(1)).isEqualTo("charmander");
    }

    @Test
    @DisplayName("Get pokemons from API should return emptu list when API response is null")
    void getPokemonsFromApi_shouldReturnEmptyList_whenApiResponseIsNull() {
        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                any(),
                eq(PokeApiResponse.class))
        ).thenReturn(ResponseEntity.ok(null));

        List<String> result = pokemonClient.getPokemonsFromApi();

        Assertions.assertThat(result)
                .isEmpty();
    }

    @Test
    @DisplayName("Get pokemons from API should return emptu list when results are null")
    void getPokemonsFromApi_shouldReturnEmptyList_whenResultsAreNull() {
        PokeApiResponse mockResponse = new PokeApiResponse();
        mockResponse.setResults(null);

        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                any(),
                eq(PokeApiResponse.class))
        ).thenReturn(ResponseEntity.ok(mockResponse));

        List<String> result = pokemonClient.getPokemonsFromApi();

        Assertions.assertThat(result)
                .isEmpty();
    }
}
