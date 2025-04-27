package com.furia.chat.controller;

import com.furia.chat.model.Fan;
import com.furia.chat.service.FanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class FanController {
    @Autowired
    private FanService fanService;

    @GetMapping
    public ResponseEntity<Iterable<Fan>> getAll(){
        return ResponseEntity.ok(fanService.getAll());
    }

    @PostMapping
    public ResponseEntity<Fan> save(@RequestBody Fan fan){
        Fan _fan = fanService.save(fan);
        if (_fan != null) {
            return ResponseEntity.status(201).body(_fan);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Fan> edit(@RequestBody Fan fan){
        Fan _fan = fanService.edit(fan);
        if (_fan != null) {
            return ResponseEntity.ok(_fan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Fan> delete(@RequestBody String uid){
        Fan fan = fanService.delete(uid);
        if (fan != null) {
            return ResponseEntity.ok(fan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
