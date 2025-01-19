package com.looqbox.challenge.controller;

import com.looqbox.challenge.dto.PokemonDTO;
import com.looqbox.challenge.dto.PokemonResultsDTO;
import com.looqbox.challenge.dto.PokemonWithHighlightDTO;
import com.looqbox.challenge.service.PokemonService;
import com.looqbox.challenge.sort.MergeSort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
class PokemonControllerTest {

    @InjectMocks
    private PokemonController controller;

    @Mock
    private PokemonService service;

    @BeforeEach
    void setUp() {
        List<String> pokemons = List.of("pikachu", "bulbasaur", "charmander", "squirtle");
        PokemonResultsDTO pokemonResultsDTO = new PokemonResultsDTO(pokemons);

        BDDMockito.given(service.getPokemons(ArgumentMatchers.anyString(), ArgumentMatchers.any(MergeSort.SortType.class)))
                .willReturn(pokemonResultsDTO);

        PokemonWithHighlightDTO pokemonWithHighlightDTO = new PokemonWithHighlightDTO(List.of(
                new PokemonDTO("pidgey", "<pre>PID</pre>gey"),
                new PokemonDTO("pidgeot", "<pre>PID</pre>geot"))
        );

        BDDMockito.given(service.getPokemonsWithHighlight(ArgumentMatchers.anyString(), ArgumentMatchers.any(MergeSort.SortType.class)))
                .willReturn(pokemonWithHighlightDTO);
    }

    @Test
    @DisplayName("get pokemon returns list of all pokemons when successful")
    void getPokemon_ReturnsListOfAllPokemons_WhenSuccessful() {
        PokemonDTO pikachu = new PokemonDTO("pikachu");

        PokemonResultsDTO pokemonResultsDTO = controller.getPokemons("", MergeSort.SortType.ALPHABETICAL).getBody();

        Assertions.assertThat(pokemonResultsDTO).isNotNull();
        Assertions.assertThat(pokemonResultsDTO.getResults())
                .isNotNull()
                .isNotEmpty()
                .hasSize(4);
        Assertions.assertThat(pokemonResultsDTO.getResults().get(0)).isEqualTo(pikachu.getName());
    }

    @Test
    @DisplayName("get pokemon with highlight returns list of all pokemons when successful")
    void getPokemonWithHighlight_ReturnsListOfAllPokemons_WhenSuccessful() {
        PokemonDTO pidgey = new PokemonDTO("pidgey", "<pre>PID</pre>gey");

        PokemonWithHighlightDTO pokemonWithHighlightDTO = controller.getPokemonsWithHighlight("", MergeSort.SortType.ALPHABETICAL).getBody();

        Assertions.assertThat(pokemonWithHighlightDTO).isNotNull();
        Assertions.assertThat(pokemonWithHighlightDTO.getResults())
                .isNotNull()
                .isNotEmpty()
                .hasSize(2);
        Assertions.assertThat(pokemonWithHighlightDTO.getResults().get(0).getName()).isEqualTo(pidgey.getName());
        Assertions.assertThat(pokemonWithHighlightDTO.getResults().get(0).getHighlightedName()).isEqualTo(pidgey.getHighlightedName());
    }

    @Test
    @DisplayName("get pokemon returns empty list when query does not match with any name")
    void getPokemon_ReturnsEmptyList_WhenQueryDoesNotMatchWithAnyName() {
        BDDMockito.given(service.getPokemons(ArgumentMatchers.anyString(), ArgumentMatchers.any(MergeSort.SortType.class)))
                .willReturn(new PokemonResultsDTO(List.of()));

        PokemonResultsDTO pokemonResultsDTO = controller.getPokemons("", MergeSort.SortType.ALPHABETICAL).getBody();

        Assertions.assertThat(pokemonResultsDTO).isNotNull();
        Assertions.assertThat(pokemonResultsDTO.getResults())
                .isEmpty();
    }

    @Test
    @DisplayName("get pokemon with highlight returns empty list when query does not match with any name")
    void getPokemonWithHighlight_ReturnsEmptyList_WhenQueryDoesNotMatchWithAnyName() {
        BDDMockito.given(service.getPokemonsWithHighlight(ArgumentMatchers.anyString(), ArgumentMatchers.any(MergeSort.SortType.class)))
                .willReturn(new PokemonWithHighlightDTO(List.of()));

        PokemonWithHighlightDTO pokemonWithHighlightDTO = controller.getPokemonsWithHighlight("", MergeSort.SortType.ALPHABETICAL).getBody();

        Assertions.assertThat(pokemonWithHighlightDTO).isNotNull();
        Assertions.assertThat(pokemonWithHighlightDTO.getResults())
                .isEmpty();
    }

}