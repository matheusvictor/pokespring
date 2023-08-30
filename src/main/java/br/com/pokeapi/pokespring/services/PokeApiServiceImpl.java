package br.com.pokeapi.pokespring.services;

import br.com.pokeapi.pokespring.config.PokeApiConfigProperties;
import br.com.pokeapi.pokespring.dto.PokemonDTO;
import br.com.pokeapi.pokespring.dto.PokemonIdsAndNamesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Service
public class PokeApiServiceImpl implements IPokeApiService {

    @Autowired
    PokeApiConfigProperties pokeApiConfig;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Map<String, String>> getPokemonIdsAndNames(String limit, String offset) {

        String urlWithQueryParams = UriComponentsBuilder.fromHttpUrl(pokeApiConfig.getBaseUrl())
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .build()
                .toString();

        PokemonIdsAndNamesDTO allPokemonNamesList =
                this.restTemplate.getForObject(urlWithQueryParams, PokemonIdsAndNamesDTO.class);

        return getIdAndNames(allPokemonNamesList);
    }

    private List<Map<String, String>> getIdAndNames(PokemonIdsAndNamesDTO allPokemonNamesList) {
        List<Map<String, String>> pokemonIdAndNames = new ArrayList<>();

        if (allPokemonNamesList != null) {
            for (int index = 1; index <= allPokemonNamesList.getResults().size() - 1; index++) {

                Map<String, String> pokemonInfo = allPokemonNamesList.getResults().get(index);

                TreeMap<String, String> selectedPokemonInfo = new TreeMap<>();

                selectedPokemonInfo.put("id", String.valueOf(index));
                String name = pokemonInfo.get("name");
                selectedPokemonInfo.put("name", name);

                pokemonIdAndNames.add(selectedPokemonInfo);
            }
        }
        return pokemonIdAndNames;
    }

    @Override
    public PokemonDTO getById(String id) {
        // TODO: Tratar exeções
        String urlWithPathParam = UriComponentsBuilder.fromHttpUrl(pokeApiConfig.getBaseUrl())
                .path(String.format("/%s", id))
                .build()
                .toString();
        return restTemplate.getForObject(urlWithPathParam, PokemonDTO.class);
    }

}
