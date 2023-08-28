package br.com.pokeapi.pokespring.controllers;

import br.com.pokeapi.pokespring.dto.PokemonDTO;
import br.com.pokeapi.pokespring.services.IPokeApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {
    IPokeApiService pokeApiService;

    public PokemonController(IPokeApiService pokeApiService) {
        this.pokeApiService = pokeApiService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonDTO> getById(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(this.pokeApiService.getById(id));
    }

}
