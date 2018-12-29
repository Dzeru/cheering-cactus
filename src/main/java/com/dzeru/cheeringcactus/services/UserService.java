package com.dzeru.cheeringcactus.services;

import com.dzeru.cheeringcactus.entities.User;
import com.dzeru.cheeringcactus.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService
{
    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String uuid) throws UsernameNotFoundException
    {
        User user = userRepo.findByUsername(uuid);
        return user;
    }
}
