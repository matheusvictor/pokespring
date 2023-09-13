package br.com.pokeapi.pokespring.services.impl;

import br.com.pokeapi.pokespring.config.PokeApiConfigProperties;
import br.com.pokeapi.pokespring.dto.AbilityDTO;
import br.com.pokeapi.pokespring.dto.PokemonDTO;
import br.com.pokeapi.pokespring.dto.PokemonIdsAndNamesDTO;
import br.com.pokeapi.pokespring.dto.TypeDTO;
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

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class PokeApiServiceImplTest {

    private static final Integer BULBASAUR_ID = 1;
    private static final Integer INVALID_POKEMON_ID = 999999;
    private static final String LIMIT = "2";
    private static final String OFFSET = "0";
    private static final String POKEMON_NAME = "bulbasaur";
    private static final String BASE_URL = "https://pokeapi.co/api/v2/pokemon";
    private static final String EXCEPTION_MESSAGE = "Houve um erro ao tentar realizar a requisição!";

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
                .thenReturn(BASE_URL);
    }

    @Test
    @DisplayName("Should ensure base URL is not null")
    void baseUrlIsNotNull() {
        assertNotNull(pokeApiConfigProperties.getBaseUrl());
    }

    @Test
    @DisplayName("Should get bulbasaur when ID is 1")
    void getPokemonInformationById() {

        // when
        Integer pokemonId = BULBASAUR_ID;

        String urlWithPathParam = UriComponentsBuilder
                .fromHttpUrl(pokeApiConfigProperties.getBaseUrl())
                .path(String.format("/%s", pokemonId))
                .build()
                .toString();

        PokemonDTO expected = this.createBulbasaurMock();

        // then
        assertEquals(String.format(pokeApiConfigProperties.getBaseUrl() + "/%s", pokemonId), urlWithPathParam);

        PokemonDTO response = pokeApiService.getByIdOrName(String.valueOf(pokemonId));

        assertNotNull(response);
        assertEquals(expected, response);
    }

    @Test
    @DisplayName("Should get bulbasaur informatins when pokemon's name is bulbasaur")
    void getPokemonInformationByName() {

        // when
        String pokemonName = POKEMON_NAME;

        String urlWithPathParam = UriComponentsBuilder
                .fromHttpUrl(pokeApiConfigProperties.getBaseUrl())
                .path(String.format("/%s", pokemonName))
                .build()
                .toString();

        PokemonDTO expected = this.createBulbasaurMock();

        // then
        assertEquals(String.format(pokeApiConfigProperties.getBaseUrl() + "/%s", pokemonName), urlWithPathParam);

        PokemonDTO response = pokeApiService.getByIdOrName(pokemonName);

        assertNotNull(response);
        assertEquals(expected, response);
    }

    @Test
    @DisplayName("Should throws an exception when ID doesn't exists")
    void throwExceptionWhenPokemonIdDoesntExists() {

        // when
        Integer pokemonId = INVALID_POKEMON_ID;

        String urlWithPathParam = UriComponentsBuilder
                .fromHttpUrl(pokeApiConfigProperties.getBaseUrl())
                .path(String.format("/%s", pokemonId))
                .build()
                .toString();

        // then
        assertEquals(String.format(pokeApiConfigProperties.getBaseUrl() + "/%s", pokemonId), urlWithPathParam);

        assertThrowsExactly(
                RuntimeException.class,
                () -> pokeApiService.getByIdOrName(String.valueOf(pokemonId)),
                EXCEPTION_MESSAGE
        );
    }

    @Test
    @DisplayName("Should return a list with all Pokemons IDs and names")
    void getAllPokemonsIdsAndNames() {

        List<Map<String, String>> expected = this.createPokemonListMock();
        List<Map<String, String>> response =
                this.pokeApiService.getPokemonIdsAndNames(LIMIT, OFFSET);

        // then
        assertEquals(expected, response);
    }

    private PokemonDTO createBulbasaurMock() {

        PokemonDTO bulbasaurMock = new PokemonDTO();

        TypeDTO firstType = new TypeDTO();
        firstType.setTypeName(Map.of("name", "grass"));

        TypeDTO secondType = new TypeDTO();
        secondType.setTypeName(Map.of("name", "poison"));

        AbilityDTO firstAbility = new AbilityDTO();
        firstAbility.setAbilityName(Map.of("name", "overgrow"));

        AbilityDTO secondAbility = new AbilityDTO();
        secondAbility.setAbilityName(Map.of("name", "chlorophyll"));

        bulbasaurMock.setId(1);
        bulbasaurMock.setName(POKEMON_NAME);
        bulbasaurMock.setTypes(List.of(firstType, secondType));
        bulbasaurMock.setAbilities(List.of(firstAbility, secondAbility));

        return bulbasaurMock;
    }

    private List<Map<String, String>> createPokemonListMock() {
        PokemonIdsAndNamesDTO mock = new PokemonIdsAndNamesDTO();

        mock.setResults(
                List.of(
                        Map.of("id", "1", "name", "bulbasaur"),
                        Map.of("id", "2", "name", "ivysaur")
                )
        );

        return mock.getResults();
    }
}
