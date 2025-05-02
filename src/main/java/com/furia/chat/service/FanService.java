package com.furia.chat.service;

import com.furia.chat.dto.FanDTO;
import com.furia.chat.mapper.FanMapper;
import com.furia.chat.model.Fan;
import com.furia.chat.repository.FanRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FanService {
    @Autowired
    private FanRepository fanRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(13);

    public FanDTO register(Fan fan) throws EntityExistsException, IllegalArgumentException {
        Optional<Fan> found = fanRepository.findById(fan.getUsername());
        if (found.isPresent()) {
            throw new EntityExistsException("This fan already exists");
        }

        if (fan.getPassword().length() < 6){
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }

        fan.setPassword(encoder.encode(fan.getPassword()));
        fan.setRoleType("ROLE_FAN");
        fanRepository.save(fan);
        return FanMapper.toFanDTO(fan);
    }

    protected Fan findByUsername(String username){
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
            fanDTOs.add(FanMapper.toFanDTO(fan));
        }
        return fanDTOs;
    }

    public FanDTO edit(Fan fan) throws ClassNotFoundException, AccessDeniedException {
        if (isUserUnauthorizedToModify(fan.getUsername())) {
            throw new AccessDeniedException("Not authorized to edit this fan");
        }

        Optional<Fan> _fan = fanRepository.findById(fan.getUsername());
        if (_fan.isPresent()){
            if (fan.getEmail() != null) _fan.get().setEmail(fan.getEmail());
            if (fan.getUsername() != null) _fan.get().setUsername(fan.getUsername());
            if (fan.getPassword() != null) _fan.get().setPassword(encoder.encode(fan.getPassword()));
            if (fan.getProfilePicture() != null) _fan.get().setProfilePicture(fan.getProfilePicture());
            if (fan.getBio() != null) _fan.get().setBio(fan.getBio());
            fanRepository.save(_fan.get());
            return _fan.map(FanMapper::toFanDTO).get();
        }

        throw new ClassNotFoundException("Fan not found");
    }

    private boolean isUserUnauthorizedToModify(String targetUsername) {
        String loggedUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        return !loggedUsername.equals(targetUsername);
    }

    public FanDTO delete(String username) throws ClassNotFoundException, AccessDeniedException {
        if (isUserUnauthorizedToDelete(username)) {
            throw new AccessDeniedException("Not authorized to delete this fan");
        }

        Optional<Fan> fan = fanRepository.findById(username);
        if (fan.isPresent()) {
            fanRepository.deleteById(username);
            return FanMapper.toFanDTO(fan.get());
        }

        throw new ClassNotFoundException("Fan not found");
    }

    private boolean isUserUnauthorizedToDelete(String targetUsername) {
        Authentication logged = SecurityContextHolder.getContext().getAuthentication();
        String loggedUsername = logged.getName();
        String role = logged.getAuthorities().iterator().next().getAuthority();
        return !loggedUsername.equals(targetUsername) && !role.equals("ROLE_ADMIN");
    }

}


