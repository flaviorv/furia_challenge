package com.furia.chat.controller;

import com.furia.chat.dto.FanDTO;
import com.furia.chat.model.Fan;
import com.furia.chat.service.FanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FanController {

    @Autowired
    private FanService fanService;

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
