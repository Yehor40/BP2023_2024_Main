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
                                .requestMatchers("/").hasRole("ADMIN")
                                .requestMatchers("/users").hasRole("ADMIN")
                                .requestMatchers("/users/{id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST,"/create").hasRole("ADMIN")
                                .requestMatchers("/users/create").hasRole("ADMIN")
                                .requestMatchers("/users/{id}/edit").hasRole("ADMIN")
                                .requestMatchers("/users/{id}/delete").hasRole("ADMIN")
                                .requestMatchers("/about").permitAll()
                                .requestMatchers("/indexUser").permitAll()
                                .requestMatchers("/evidences").hasRole("USER")
                                .requestMatchers("/evidences/{id}").hasRole("USER")
                                .requestMatchers("/evidences/create").hasRole("USER")
                                .requestMatchers("/createEvidence").hasRole("USER")
                                .requestMatchers("/evidenceEdit").hasRole("USER")
                                .requestMatchers("/evidences/{id}/edit").hasRole("USER")
                                .requestMatchers("/redirect:users").hasRole("USER")
                                .requestMatchers("/redirect:evidences").hasRole("USER")
                                .requestMatchers("/evidences/**").hasRole("USER")
                                .requestMatchers("/evidence/download/all").hasRole("USER")
                                .requestMatchers("/evidence/download/{id}").hasRole("USER")
                              //  .requestMatchers("/me").permitAll()



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
                )

        ;
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
