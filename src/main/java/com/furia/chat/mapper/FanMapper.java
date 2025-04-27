package com.furia.chat.mapper;

import com.furia.chat.dto.FanDTO;
import com.furia.chat.model.Fan;

public class FanMapper {
    public static FanDTO fanToFanDTO(Fan fan){
        return new FanDTO(
                fan.getName(),
                fan.getEmail(),
                fan.getUsername(),
                fan.getProfilePictureUrl(),
                fan.getBio()
        );
    }
}
