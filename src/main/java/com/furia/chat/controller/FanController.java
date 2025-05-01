package com.furia.chat.controller;

import com.furia.chat.dto.FanDTO;
import com.furia.chat.model.Fan;
import com.furia.chat.service.FanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class FanController {

    @Autowired
    private FanService fanService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/fans")
    public ResponseEntity<Iterable<FanDTO>> getAll() {
        return ResponseEntity.ok(fanService.getAll());
    }

    @PostMapping("/register")
    public ResponseEntity<FanDTO> register(@RequestBody Fan fan){
        FanDTO _fan = fanService.register(fan);
        if (_fan != null) {
            return ResponseEntity.status(201).body(_fan);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Fan fan){
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(fan.getUsername(), fan.getPassword()));
        if (auth.isAuthenticated()) {
            return ResponseEntity.ok("Login Success");
        }
        return ResponseEntity.status(401).build();
    }

    @PutMapping("/edit")
    public ResponseEntity<FanDTO> edit(@RequestBody Fan fan){
        FanDTO _fan = fanService.edit(fan);
        if (_fan != null) {
            return ResponseEntity.ok(_fan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete-account")
    public ResponseEntity<FanDTO> delete(@RequestBody Fan fan){
        String username = fan.getUsername();
        FanDTO _fan = fanService.delete(username);
        if (_fan != null) {
            return ResponseEntity.ok(_fan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
