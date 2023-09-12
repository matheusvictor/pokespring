package br.com.pokeapi.pokespring.services.impl;

import br.com.pokeapi.pokespring.config.PokeApiConfigProperties;
import br.com.pokeapi.pokespring.dto.PokemonDTO;
import br.com.pokeapi.pokespring.dto.PokemonIdsAndNamesDTO;
import br.com.pokeapi.pokespring.services.IPokeApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class PokeApiServiceImpl implements IPokeApiService {

    @Autowired
    PokeApiConfigProperties pokeApiConfig;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Map<String, String>> getPokemonIdsAndNames(String limit, String offset) {

        PokemonIdsAndNamesDTO allPokemonNamesList = new PokemonIdsAndNamesDTO();

        try {
            String urlWithQueryParams = UriComponentsBuilder
                    .fromHttpUrl(pokeApiConfig.getBaseUrl())
                    .queryParam("limit", limit)
                    .queryParam("offset", offset)
                    .build()
                    .toString();

            ResponseEntity<PokemonIdsAndNamesDTO> response = this.restTemplate.exchange(
                    urlWithQueryParams,
                    HttpMethod.GET,
                    new HttpEntity<>(new HttpHeaders()),
                    PokemonIdsAndNamesDTO.class
            );

            allPokemonNamesList = response.getBody();

//            if (allPokemonNamesList != null) {
//                return allPokemonNamesList.getResults();
//            }
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            allPokemonNamesList.setResults(new ArrayList<>());
            throw new RuntimeException("Houve um erro ao tentar realizar a requisição!");
        }
        return allPokemonNamesList.getResults();
    }

    private List<Map<String, String>> getIdAndNames(PokemonIdsAndNamesDTO allPokemonNamesList) {
        List<Map<String, String>> pokemonIdAndNames = new ArrayList<>();

        if (allPokemonNamesList != null) {
            for (int index = 0; index < allPokemonNamesList.getResults().size(); index++) {

                Map<String, String> pokemonInfo = allPokemonNamesList.getResults().get(index);

                TreeMap<String, String> selectedPokemonInfo = new TreeMap<>();

                Integer pokemonId = index + 1;
                String name = pokemonInfo.get("name");

                selectedPokemonInfo.put("id", String.valueOf(pokemonId));
                selectedPokemonInfo.put("name", name);

                pokemonIdAndNames.add(selectedPokemonInfo);
            }
        }
        return pokemonIdAndNames;
    }

    @Override
    public PokemonDTO getByIdOrName(String value) {

        PokemonDTO pokemonDTO = new PokemonDTO();

        try {
            String urlWithPathParam = UriComponentsBuilder
                    .fromHttpUrl(pokeApiConfig.getBaseUrl())
                    .path(String.format("/%s", value))
                    .build()
                    .toString();

            ResponseEntity<PokemonDTO> response = this.restTemplate.exchange(
                    urlWithPathParam,
                    HttpMethod.GET,
                    new HttpEntity<>(new HttpHeaders()),
                    PokemonDTO.class
            );

            pokemonDTO = response.getBody();

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new RuntimeException("Houve um erro ao tentar realizar a requisição!");
        }

        return pokemonDTO;
    }

}
