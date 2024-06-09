package com.code.main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    //USE FOR BASIC AUTHENTICATION
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().authorizeHttpRequests((authorize) -> authorize.
                // anyRequest().authenticated()//
                        requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                .anyRequest().authenticated()

        ).httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }


    //In Memory Authentication
    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails userone = User.builder()
                .username("hector")
                .password(passwordEncoder().encode("annie"))
                .roles("ADMIN")
                .build();

        UserDetails usertwo = User.builder()
                .username("nic")
                .password(passwordEncoder().encode("bridget"))
                .roles("SALES")
                .build();

        return new InMemoryUserDetailsManager(userone, usertwo);
    }

}
