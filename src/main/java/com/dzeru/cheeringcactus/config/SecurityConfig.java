package com.dzeru.cheeringcactus.config;

import com.dzeru.cheeringcactus.services.AuthProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private AuthProvider authProvider;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity
                .authorizeRequests()
                .antMatchers("/resources/**", "/", "/newcactus", "/greeting").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/").loginProcessingUrl("/login").defaultSuccessUrl("/cactus").failureUrl("/?error").permitAll()
                .and().logout().logoutSuccessUrl("/").permitAll();
    }

    @Override
    public void configure(WebSecurity webSecurity)
    {
        webSecurity.ignoring().antMatchers("/css/**", "/js/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
    {
        auth.authenticationProvider(authProvider);
    }
}
