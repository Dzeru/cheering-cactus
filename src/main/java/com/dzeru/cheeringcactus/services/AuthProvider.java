package com.dzeru.cheeringcactus.services;

import com.dzeru.cheeringcactus.entities.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class AuthProvider implements AuthenticationProvider
{
    final static Logger logger = Logger.getLogger(AuthProvider.class);

    @Autowired
    UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        String uuid = authentication.getName();
        User user = (User) userService.loadUserByUsername(uuid);
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        logger.info("User with uuid = " + uuid + " try to log in");

        return new UsernamePasswordAuthenticationToken(user, "", authorities);
    }

    @Override
    public boolean supports(Class<?> arg)
    {
        return true;
    }
}
