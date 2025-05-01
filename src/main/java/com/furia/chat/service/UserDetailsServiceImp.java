package com.furia.chat.service;

import com.furia.chat.dto.FanDTO;
import com.furia.chat.model.Fan;
import com.furia.chat.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private FanService fanService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Fan fan = fanService.findByUsername(username);

        if (fan == null) {
            System.out.println("Fan not found");
            throw new UsernameNotFoundException("Fan not found");
        }
        return new UserPrincipal(fan);
    }

}
