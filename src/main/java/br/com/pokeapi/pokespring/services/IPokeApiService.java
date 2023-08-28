package br.com.pokeapi.pokespring.services;

import br.com.pokeapi.pokespring.dto.PokemonDTO;


public interface IPokeApiService {

    PokemonDTO getById(String id);

}
