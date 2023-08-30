package br.com.pokeapi.pokespring.services;

import br.com.pokeapi.pokespring.config.PokeApiConfigProperties;
import br.com.pokeapi.pokespring.dto.PokemonDTO;
import br.com.pokeapi.pokespring.dto.PokemonIdsAndNamesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
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
    public ResponseEntity<List<Map<String, String>>> getPokemonIdsAndNames(String limit, String offset) {

        String urlWithQueryParams = UriComponentsBuilder.fromHttpUrl(pokeApiConfig.getBaseUrl())
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .build()
                .toString();

        PokemonIdsAndNamesDTO allPokemonNamesList =
                this.restTemplate.getForObject(urlWithQueryParams, PokemonIdsAndNamesDTO.class);

        try {
            return new ResponseEntity<>(
                    this.getIdAndNames(allPokemonNamesList),
                    HttpStatus.OK
            );
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(
                    this.getIdAndNames(allPokemonNamesList),
                    e.getStatusCode()
            );
        }
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
    public ResponseEntity<PokemonDTO> getByIdOrName(String value) {
        String urlWithPathParam = UriComponentsBuilder.fromHttpUrl(pokeApiConfig.getBaseUrl())
                .path(String.format("/%s", value))
                .build()
                .toString();

        try {
            return new ResponseEntity<>(
                    restTemplate.getForObject(urlWithPathParam, PokemonDTO.class),
                    HttpStatus.OK
            );
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(
                    new PokemonDTO(), e.getStatusCode()
            );
        }
    }

}
