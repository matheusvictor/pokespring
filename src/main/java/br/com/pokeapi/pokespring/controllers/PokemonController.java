package br.com.pokeapi.pokespring.controllers;

import br.com.pokeapi.pokespring.models.Pokemon;
import br.com.pokeapi.pokespring.services.IPokeApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {

    @Autowired
    IPokeApiService pokeApiService;

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getById(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(this.pokeApiService.getById(id));
    }

}
