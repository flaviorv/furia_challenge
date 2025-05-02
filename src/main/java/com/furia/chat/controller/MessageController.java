package com.furia.chat.controller;

import com.furia.chat.dto.ChatMessageRequest;
import com.furia.chat.model.ChatMessage;
import com.furia.chat.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chat")
public class MessageController {
    @Autowired
    private ChatMessageService chatMessageService;

    @PostMapping
    public ResponseEntity<?> sendMessage(@RequestBody ChatMessageRequest message) {
        try {
            ChatMessage saved = chatMessageService.save(message);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("cause", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<ChatMessage>> getMessages() {
        return ResponseEntity.status(HttpStatus.OK).body(chatMessageService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable String id) {
        try {
            chatMessageService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (ClassNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
