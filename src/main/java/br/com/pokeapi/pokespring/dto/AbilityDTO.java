package br.com.pokeapi.pokespring.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AbilityDTO {

    @JsonProperty("ability")
    private Map<String, String> abilityName;

    public String getAbilityName() {
        return this.abilityName.get("name");
    }
}
