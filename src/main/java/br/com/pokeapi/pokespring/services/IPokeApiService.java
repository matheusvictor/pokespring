package br.com.pokeapi.pokespring.services;

import br.com.pokeapi.pokespring.dto.PokemonDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;


public interface IPokeApiService {

    ResponseEntity<List<Map<String, String>>> getPokemonIdsAndNames(String limit, String offset);

    ResponseEntity<PokemonDTO> getByIdOrName(String value);

}
