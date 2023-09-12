package br.com.pokeapi.pokespring.controllers;

import br.com.pokeapi.pokespring.dto.PokemonDTO;
import br.com.pokeapi.pokespring.services.IPokeApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {
    IPokeApiService pokeApiService;

    @Autowired
    public PokemonController(IPokeApiService pokeApiService) {
        this.pokeApiService = pokeApiService;
    }

    @GetMapping
    public List<Map<String, String>> getPokemonIdsAndNames(
            @RequestParam(name = "limit", defaultValue = "10", required = false) String limit,
            @RequestParam(name = "offset", defaultValue = "0", required = false) String offset
    ) {
        return this.pokeApiService.getPokemonIdsAndNames(limit, offset);
    }

    @GetMapping("/{value}")
    public PokemonDTO getById(@PathVariable(name = "value") String value) {
        return this.pokeApiService.getByIdOrName(value);
    }

}
