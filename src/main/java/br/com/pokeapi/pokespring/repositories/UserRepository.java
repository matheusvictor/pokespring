package br.com.pokeapi.pokespring.repositories;

import br.com.pokeapi.pokespring.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
