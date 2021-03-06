package com.example.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder)throws Exception {

        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser("chandan" ).password("chand").roles("USER")
        .and().withUser("ali").password("ali").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
                .antMatchers("/api/test").hasAnyRole("USER")
                .antMatchers("/api/admin").hasAnyRole("ADMIN")
                //.anyRequest().fullyAuthenticated()
                .and().httpBasic();
                //.and().httpBasic();
        httpSecurity.csrf().disable();
    }



    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    }


