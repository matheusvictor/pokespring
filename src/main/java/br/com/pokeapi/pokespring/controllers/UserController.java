package br.com.pokeapi.pokespring.controllers;

import br.com.pokeapi.pokespring.domain.model.User;
import br.com.pokeapi.pokespring.dto.UserData;
import br.com.pokeapi.pokespring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @Transactional
    public void create(@RequestBody UserData data) {
        userRepository.save(new User(data));
    }

}
