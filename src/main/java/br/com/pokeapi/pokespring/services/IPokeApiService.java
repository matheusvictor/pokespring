package br.com.pokeapi.pokespring.services;

import br.com.pokeapi.pokespring.dto.PokemonDTO;

import java.util.List;
import java.util.Map;


public interface IPokeApiService {

    List<Map<String, String>> getPokemonIdsAndNames(String limit, String offset);

    PokemonDTO getByIdOrName(String value);

}
