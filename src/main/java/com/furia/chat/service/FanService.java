package com.furia.chat.service;

import com.furia.chat.dto.FanDTO;
import com.furia.chat.mapper.FanMapper;
import com.furia.chat.model.Fan;
import com.furia.chat.repository.FanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FanService {
    @Autowired
    private FanRepository fanRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(13);

    public FanDTO register(Fan fan){
        Optional<Fan> found = fanRepository.findById(fan.getUsername());
        if (found.isPresent()) {
            System.out.println("Existent username");
            return null;
        }
        if (fan.getPassword().length() >= 6){
            fan.setPassword(encoder.encode(fan.getPassword()));
            fan.setRoleType("ROLE_FAN");
            fanRepository.save(fan);
            System.out.println("Fan "+fan.getUsername()+" saved");
            return FanMapper.fanToFanDTO(fan);
        }
        System.out.println("Password should be more than 6 characters");
        return null;
    }

    public Fan findByUsername(String username){
        Optional<Fan> fan = fanRepository.findById(username);
        if (fan.isPresent()){
            return fan.get();
        }
        return null;
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
        Optional<Fan> _fan = fanRepository.findById(fan.getUsername());
        if (_fan.isPresent()){
            if (fan.getEmail() != null) _fan.get().setEmail(fan.getEmail());
            if (fan.getUsername() != null) _fan.get().setUsername(fan.getUsername());
            if (fan.getPassword() != null) _fan.get().setPassword(encoder.encode(fan.getPassword()));
            if (fan.getProfilePicture() != null) _fan.get().setProfilePicture(fan.getProfilePicture());
            if (fan.getBio() != null) _fan.get().setBio(fan.getBio());
            fanRepository.save(_fan.get());
            return _fan.map(FanMapper::fanToFanDTO).get();
        }
        return null;
    }

    public FanDTO delete(String username){
        Optional<Fan> fan = fanRepository.findById(username);
        if (fan.isPresent()) {
            fanRepository.deleteById(username);
            return FanMapper.fanToFanDTO(fan.get());
        } else {
          return null;
        }
    }
}


