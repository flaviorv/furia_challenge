package com.furia.chat.service;

import com.furia.chat.dto.ChatMessageRequest;
import com.furia.chat.model.ChatMessage;
import com.furia.chat.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChatMessageService {
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public ChatMessage save(ChatMessageRequest message) throws IllegalArgumentException {
        if (message.message() == null || message.message().trim().isEmpty()) {
            throw new IllegalArgumentException("Message should be not empty");
        }

        String answer = ChatMessage.answer(message.message());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String sender = auth.getName();
        if (auth.getAuthorities().iterator().next().getAuthority().equals("ROLE_ADMIN")) {
            sender = "FURIA";
        }
        ChatMessage filled = new ChatMessage(sender, message.message());

        if(answer != null) {
            ChatMessage answered = new ChatMessage("FURIA", answer);
            chatMessageRepository.save(filled);
            return chatMessageRepository.save(answered);
        }

        return chatMessageRepository.save(filled);
    }

    public List<ChatMessage> getAll() {
        return chatMessageRepository.findAll();
    }

    public void deleteById(String id) throws ClassNotFoundException, AccessDeniedException {
        ChatMessage chatMessage = chatMessageRepository.findById(id)
                .orElseThrow(() -> new ClassNotFoundException("Not found"));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String sender = auth.getName();
        String role = auth.getAuthorities().iterator().next().getAuthority();
        if (!chatMessage.getSender().equals(sender) && !role.equals("ROLE_ADMIN")) {
            throw new AccessDeniedException("No permission to delete this message");
        }
        chatMessageRepository.delete(chatMessage);
    }
}
