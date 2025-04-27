package com.furia.chat.service;

import com.furia.chat.model.Fan;
import com.furia.chat.repository.FanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class FanService {
    @Autowired
    private FanRepository fanRepository;

    public Fan save(Fan fan){
        Optional<Fan> found = fanRepository.findById(fan.getFirebaseUid());
        if (found.isPresent()){
            return null;
        }
        fanRepository.save(fan);
        return fan;
    }

    public Iterable<Fan> getAll(){
        return fanRepository.findAll();
    }

    public Fan findByUid(String uid) {
        return fanRepository.findById(uid).orElse(null);
    }

    public Fan edit(Fan fan) {
        Optional<Fan> _fan = fanRepository.findById(fan.getFirebaseUid());
        if (_fan.isPresent()){
            if (fan.getName() != null) _fan.get().setName(fan.getName());
            if (fan.getEmail() != null) _fan.get().setEmail(fan.getEmail());
            if (fan.getUsername() != null) _fan.get().setUsername(fan.getUsername());
            if (fan.getProfilePictureUrl() != null) _fan.get().setProfilePictureUrl(fan.getProfilePictureUrl());
            if (fan.getBio() != null) _fan.get().setBio(fan.getBio());
            return _fan.get();
        }
        return null;
    }

    public Fan delete(String uid){
        Fan fan = findByUid(uid);
        if (fan != null) {
            fanRepository.deleteById(uid);
            return fan;
        } else {
          return null;
        }
    }

}


