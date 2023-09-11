package br.com.pokeapi.pokespring.controllers;

import br.com.pokeapi.pokespring.config.PokeApiConfigProperties;
import br.com.pokeapi.pokespring.dto.PokemonDTO;
import br.com.pokeapi.pokespring.services.IPokeApiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class PokemonControllerTest {

    @Autowired
    PokeApiConfigProperties pokeApiConfig;

    @Mock
    private RestTemplate restTemplate;


    @InjectMocks
    private IPokeApiService pokeApiService;

    PokemonControllerTest() {
    }

    @Test
    void getByIdOrName() {
        PokemonDTO pokemon = new PokemonDTO();
        ArrayList<String> types = new ArrayList<>(
                List.of("grass", "poison")
        );

        pokemon.setId(1);
        pokemon.setName("bulbasaur");

        ResponseEntity<PokemonDTO> response = new ResponseEntity<>(

        );

        String urlWithPathParam = UriComponentsBuilder.fromHttpUrl(pokeApiConfig.getBaseUrl())
                .path(String.format("/%s", 1))
                .build()
                .toString();

        Mockito
                .when(restTemplate.getForObject(urlWithPathParam, PokemonDTO.class))
                .thenReturn();
    }
}