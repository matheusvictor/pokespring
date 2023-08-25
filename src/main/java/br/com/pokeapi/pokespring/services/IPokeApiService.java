package br.com.pokeapi.pokespring.services;

import br.com.pokeapi.pokespring.models.Pokemon;


public interface IPokeApiService {

    Pokemon getById(String id);

}
