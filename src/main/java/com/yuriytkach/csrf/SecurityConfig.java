package com.yuriytkach.csrf;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
class SecurityConfig {

  @Bean
  SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
    return http
      //.csrf(config -> config.disable()) // Do not disable in stateful application
      .authorizeHttpRequests(
        authorizeHttp -> {
          authorizeHttp.requestMatchers("/").permitAll();
          authorizeHttp.requestMatchers("/favicon.icon").permitAll();
          authorizeHttp.requestMatchers("/css/*").permitAll();
          authorizeHttp.requestMatchers("/error").permitAll();
          authorizeHttp.anyRequest().authenticated();
        }
      )
      .formLogin(withDefaults())
      .logout(config -> config.logoutSuccessUrl("/"))
      .build();
  }

  @Bean
  UserDetailsManager userDetailsService() {
    return new InMemoryUserDetailsManager(
      User.withUsername("user")
        .password(passwordEncoder().encode("password"))
        .roles("user")
        .build()
    );
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  ApplicationListener<AuthenticationSuccessEvent> successListener() {
    return event -> System.out.printf(
      "[%s] %s%n", event.getAuthentication().getClass().getSimpleName(),
      event.getAuthentication().getName()
    );
  }

}
