package br.com.pokeapi.pokespring.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TypeDTO {

    @JsonProperty("type")
    private Map<String, String> typeName;

    public String getTypeName() {
        return this.typeName.get("name");
    }
}
