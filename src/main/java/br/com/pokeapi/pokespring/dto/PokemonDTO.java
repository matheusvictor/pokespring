package br.com.pokeapi.pokespring.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"id", "name", "types", "abilities"}) // Define a ordem desejada das chaves

public class PokemonDTO {

    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("name")
    private String name;

    private List<TypeDTO> types = new ArrayList<>();

    @JsonProperty(value = "types")
    public List<String> getTypeNames() {
        List<String> typeNames = new ArrayList<>();

        for (TypeDTO typeDTO : types) {
            typeNames.add(typeDTO.getTypeName());
        }
        return typeNames;
    }

    private List<AbilityDTO> abilities = new ArrayList<>();

    @JsonProperty(value = "abilities")
    public List<String> getAbilityNames() {
        List<String> abilityName = new ArrayList<>();

        for (AbilityDTO abilities : abilities) {
            abilityName.add(abilities.getAbilityName());
        }
        return abilityName;
    }
}
