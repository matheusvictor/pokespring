package br.com.pokeapi.pokespring.services.impl;

import br.com.pokeapi.pokespring.config.PokeApiConfigProperties;
import br.com.pokeapi.pokespring.dto.PokemonDTO;
import br.com.pokeapi.pokespring.services.IPokeApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class PokeApiServiceImplTest {

    @Mock
    private PokeApiConfigProperties pokeApiConfigProperties;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private IPokeApiService pokeApiService = new PokeApiServiceImpl();

    @BeforeEach
    public void setup() {
        Mockito
                .when(pokeApiConfigProperties.getBaseUrl())
                .thenReturn("https://pokeapi.co/api/v2/pokemon");
    }

    @Test
    @DisplayName("Should ensure base URL is not null")
    void testBaseUrl() {
        assertNotNull(pokeApiConfigProperties.getBaseUrl());
    }

    @Test
    @DisplayName("Should get bulbasaur when ID is 1")
    void getByIdOrName() {

        String pokemonId = "1";

        String urlWithPathParam = UriComponentsBuilder
                .fromHttpUrl(pokeApiConfigProperties.getBaseUrl())
                .path(String.format("/%s", pokemonId))
                .build()
                .toString();

        assertEquals("https://pokeapi.co/api/v2/pokemon/1", urlWithPathParam);

        PokemonDTO pokemonDTO = pokeApiService.getByIdOrName(pokemonId);

        assertNotNull(pokemonDTO);
        assertEquals("bulbasaur", pokemonDTO.getName());
    }

}