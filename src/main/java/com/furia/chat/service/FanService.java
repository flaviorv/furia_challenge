package com.furia.chat.service;

import com.furia.chat.dto.FanDTO;
import com.furia.chat.mapper.FanMapper;
import com.furia.chat.model.Fan;
import com.furia.chat.repository.FanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FanService {
    @Autowired
    private FanRepository fanRepository;

    public FanDTO save(Fan fan){
        Optional<Fan> found = fanRepository.findById(fan.getFirebaseUid());
        if (found.isPresent()){
            return null;
        }
        fanRepository.save(fan);
        return FanMapper.fanToFanDTO(fan);
    }

    public Iterable<FanDTO> getAll(){
        Iterable<Fan> fans = fanRepository.findAll();
        List<FanDTO> fanDTOs = new ArrayList<>();
        for (Fan fan : fans) {
            fanDTOs.add(FanMapper.fanToFanDTO(fan));
        }
        return fanDTOs;
    }

    public FanDTO edit(Fan fan) {
        Optional<Fan> _fan = fanRepository.findById(fan.getFirebaseUid());
        if (_fan.isPresent()){
            if (fan.getName() != null) _fan.get().setName(fan.getName());
            if (fan.getEmail() != null) _fan.get().setEmail(fan.getEmail());
            if (fan.getUsername() != null) _fan.get().setUsername(fan.getUsername());
            if (fan.getProfilePictureUrl() != null) _fan.get().setProfilePictureUrl(fan.getProfilePictureUrl());
            if (fan.getBio() != null) _fan.get().setBio(fan.getBio());
            fanRepository.save(_fan.get());
            return _fan.map(FanMapper::fanToFanDTO).get();
        }
        return null;
    }

    public FanDTO delete(String uid){
        Optional<Fan> fan = fanRepository.findById(uid);
        if (fan.isPresent()) {
            fanRepository.deleteById(uid);
            return FanMapper.fanToFanDTO(fan.get());
        } else {
          return null;
        }
    }

}


