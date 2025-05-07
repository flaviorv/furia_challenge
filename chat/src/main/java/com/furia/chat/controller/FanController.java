package com.furia.chat.controller;

import com.furia.chat.dto.FanDTO;
import com.furia.chat.model.Fan;
import com.furia.chat.service.FanService;
import com.furia.chat.service.JWTService;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.nio.file.AccessDeniedException;
import java.util.Map;

@RestController
public class FanController {

    @Autowired
    private FanService fanService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @GetMapping("/fans")
    public ResponseEntity<Iterable<FanDTO>> getAll() {
        return ResponseEntity.ok(fanService.getAll());
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Fan fan){
        try {
            FanDTO _fan = fanService.register(fan);
            return ResponseEntity.status(201).body(_fan);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("cause", e.getMessage()));

        } catch (EntityExistsException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Fan fan){
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(fan.getUsername(), fan.getPassword()));
        if (auth.isAuthenticated()) {
            String token = jwtService.generateToken(auth);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).build();
    }

    @PutMapping("/edit")
    public ResponseEntity<FanDTO> edit(@RequestBody Fan fan){
        try {
            FanDTO _fan = fanService.edit(fan);
            return ResponseEntity.ok(_fan);

        } catch (AccessDeniedException e) {
            return ResponseEntity.status(403).build();

        } catch (ClassNotFoundException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/delete-account")
    public ResponseEntity<FanDTO> delete(@RequestBody Fan fan){
        String username = fan.getUsername();
        try {
            FanDTO _fan = fanService.delete(username);
            return ResponseEntity.ok(_fan);

        } catch(AccessDeniedException e) {
            return ResponseEntity.status(403).build();

        } catch (ClassNotFoundException e) {
            return ResponseEntity.status(404).build();
        }
    }
}
