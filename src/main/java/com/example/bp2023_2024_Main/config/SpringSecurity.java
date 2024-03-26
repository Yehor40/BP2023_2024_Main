package com.example.bp2023_2024_Main.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/register/**").permitAll()
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/users").hasRole("ADMIN")
                                .requestMatchers("/users/{id}").permitAll()
                                //.requestMatchers("/users/{id}").permitAll()
                                .requestMatchers(HttpMethod.POST,"/create").permitAll()
                                .requestMatchers("/users/create").permitAll()
                                .requestMatchers("/users/{id}/edit").permitAll()
                                .requestMatchers("/users/{id}/delete").permitAll()
                                .requestMatchers("/about").permitAll()
                                .requestMatchers("/indexUser").permitAll()
                     //           .requestMatchers("/evidences").permitAll()
                       //         .requestMatchers("/evidences/{id}").permitAll()
                         //       .requestMatchers("/evidences/create").permitAll()
                                .requestMatchers("/createEvidence").permitAll()
                                .requestMatchers("/evidenceEdit").permitAll()
                                .requestMatchers("/redirect:users").permitAll()
                                .requestMatchers("/redirect:evidences").permitAll()
                                .requestMatchers("/evidences/**").permitAll()
                                .requestMatchers("/evidence/download/all").permitAll()
                                .requestMatchers("/evidence/download/{id}").permitAll()



                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/indexUser")
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
