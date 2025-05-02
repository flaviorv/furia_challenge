package com.furia.chat.service;

import com.furia.chat.dto.ChatMessageRequest;
import com.furia.chat.model.ChatMessage;
import com.furia.chat.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ChatMessageService {
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public ChatMessage save(ChatMessageRequest message) throws IllegalArgumentException {
        if (message.message() == null || message.message().trim().isEmpty()) {
            throw new IllegalArgumentException("Message should be not empty");
        }

        ChatMessage filled = new ChatMessage(message.sender(), message.message());
        return chatMessageRepository.save(filled);
    }

    public List<ChatMessage> getAll() {
        return chatMessageRepository.findAll();
    }

    public void deleteById(String id) throws ClassNotFoundException {
        ChatMessage chatMessage = chatMessageRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found"));
        chatMessageRepository.delete(chatMessage);
    }
}
