package com.furia.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
public class FanDTO {
    private String name;
    private String email;
    private String username;
    private String profilePictureUrl;
    private String bio;
}
