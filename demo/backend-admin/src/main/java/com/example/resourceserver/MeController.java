package com.example.resourceserver;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import jakarta.annotation.PostConstruct;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class MeController {


    @Autowired
    private Keycloak keycloak;




    @GetMapping("/users")
    public List<UserRepresentation> getUsers() {
        return keycloak.realm("master").users().list();
    }



    @GetMapping("/all-realms")
    public List<RealmRepresentation> getRealms() {
        return keycloak.realms().findAll();
    }


    @PostMapping("/create-user/{username}")
    public void createUser(@PathVariable String username) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(username);
        user.setEnabled(true);
        keycloak.realm("master").users().create(user);
    }














    @CrossOrigin(origins = "http://172.29.208.1:7080/angular-admin/")
    @GetMapping("/hello")
    //@PreAuthorize("hasRole('admin')")
    public String hello(Authentication connectedUser)
    {
        return "hello";
    }
}

