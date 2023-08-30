package br.com.pokeapi.pokespring.controllers;

import br.com.pokeapi.pokespring.dto.PokemonDTO;
import br.com.pokeapi.pokespring.services.IPokeApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {
    IPokeApiService pokeApiService;

    public PokemonController(IPokeApiService pokeApiService) {
        this.pokeApiService = pokeApiService;
    }

    @GetMapping
    public ResponseEntity<List<Map<String, String>>> getPokemonIdsAndNames(
            @RequestParam(name = "limit", defaultValue = "10", required = false) String limit,
            @RequestParam(name = "offset", defaultValue = "0", required = false) String offset
    ) {
        return ResponseEntity.ok(this.pokeApiService.getPokemonIdsAndNames(limit, offset));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonDTO> getById(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(this.pokeApiService.getById(id));
    }

}
