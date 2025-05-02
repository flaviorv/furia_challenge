package com.furia.chat.mapper;

import com.furia.chat.dto.FanDTO;
import com.furia.chat.model.Fan;

public class FanMapper {
    public static FanDTO toFanDTO(Fan fan){
        return new FanDTO (
                fan.getUsername(),
                fan.getEmail(),
                fan.getProfilePicture(),
                fan.getBio()
        );
    }
}
