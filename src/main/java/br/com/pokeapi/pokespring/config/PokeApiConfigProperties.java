package br.com.pokeapi.pokespring.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Data
@Configuration
@ConfigurationProperties(prefix = "pokeapi.config")
public class PokeApiConfigProperties {

    private String baseUrl;

}
