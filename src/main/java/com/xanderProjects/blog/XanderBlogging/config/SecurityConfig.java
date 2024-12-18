package com.xanderProjects.blog.XanderBlogging.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    //     http
    //         .authorizeHttpRequests((authz) -> authz
    //             .anyRequest()
    //             .authenticated()
    //         )
    //         .httpBasic(withDefaults());

    //     return http.build();
    // }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails userDetails = User.builder()
                                    .username("Suraj")
                                    .password(passwordEncoder().encode("Suraj21"))
                                    .roles("ADMIN")
                                    .build();
        UserDetails userDetails2 = User.builder()
                                    .username("Rupa")
                                    .password(passwordEncoder().encode("Rupa13"))
                                    .roles("ADMIN")
                                    .build();
        return new InMemoryUserDetailsManager(userDetails, userDetails2);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception{
        return builder.getAuthenticationManager();
    }

}
