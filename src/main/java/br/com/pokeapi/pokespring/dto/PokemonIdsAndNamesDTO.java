package br.com.pokeapi.pokespring.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonIdsAndNamesDTO {

    @JsonProperty("results")
    List<Map<String, String>> results;

}
