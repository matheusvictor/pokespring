package br.com.pokeapi.pokespring.controllers;

import br.com.pokeapi.pokespring.domain.user.AuthData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity login(@RequestBody AuthData data) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(data.username(), data.password());
        Authentication authentication = this.authenticationManager.authenticate(token);

        return ResponseEntity
                .ok()
                .build();
    }

}
