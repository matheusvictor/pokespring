package br.com.pokeapi.pokespring.domain.model;

import br.com.pokeapi.pokespring.dto.UserData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "User")
@Table(name = "USERS")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password_encoded", nullable = false)
    private String password;

    public User(UserData data) {
        this.username = data.username();
        this.password = data.password();
    }

}
