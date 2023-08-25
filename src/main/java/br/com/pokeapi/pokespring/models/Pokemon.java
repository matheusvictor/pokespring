package br.com.pokeapi.pokespring.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_POKEMONS")
public class Pokemon {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("abilities")
    private List<Object> abilities = new ArrayList<>();

    @JsonProperty("types")
    private List<Object> types = new ArrayList<>();
}
