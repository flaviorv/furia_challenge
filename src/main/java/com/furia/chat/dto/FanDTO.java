package com.furia.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
public class FanDTO {
    private String username;
    private String email;
    private String profilePicture;
    private String bio;
}
