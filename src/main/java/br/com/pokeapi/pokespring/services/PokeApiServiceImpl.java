package br.com.pokeapi.pokespring.services;

import br.com.pokeapi.pokespring.models.Pokemon;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PokeApiServiceImpl implements IPokeApiService {

    @Override
    public Pokemon getById(String id) {
        String url = String.format("https://pokeapi.co/api/v2/pokemon/%s", id);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, Pokemon.class);
    }

}
