package com.azaan.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.azaan.ui.LoginView;
import com.vaadin.flow.spring.security.VaadinSecurityConfigurer;



@Configuration
public class SecurityConfiguration {


    


    @Bean
    public SecurityFilterChain securityFilterChain(
            org.springframework.security.config.annotation.web.builders.HttpSecurity http)
            throws Exception {


        http.with(
                VaadinSecurityConfigurer.vaadin(),

                configurer -> 
                    configurer.loginView(LoginView.class)

            );


        return http.build();
    }




    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

}