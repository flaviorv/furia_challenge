package com.furia.chat.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "CHAT_MESSAGE")
@NoArgsConstructor @Data
public class ChatMessage {
    @Id
    @Column(name = "ID", length = 36)
    private String id;
    @Column(name = "SENDER", nullable = false, length = 60)
    private String sender;
    @Column(name = "MESSAGE", nullable = false, length = 300)
    private String message;
    @Column(name = "TIMESTAMP")
    private LocalDateTime timestamp;

    public ChatMessage(String sender, String message) {
        if (id == null)
            this.id = UUID.randomUUID().toString();
        if (timestamp == null)
            this.timestamp = LocalDateTime.now();
        this.sender = sender;
        this.message = message;
    }

}
