package com.furia.chat.dto;

public record ChatMessageRequest (
   String sender,
   String message
) {}
