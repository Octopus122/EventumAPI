package com.example.demo.cofiguration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfiguration(
    private val environment: Environment
) {

    @Bean
    fun userDetailService(): UserDetailsService{
        val andriodUser = User.builder()
            .username("andriod_user")
            .password(environment.getProperty("spring.security.android_user.password"))
            .roles("ANDROID")
            .build()
        val admin  = User.builder()
            .username("admin")
            .password(environment.getProperty("spring.security.admin.password"))
            .roles("ADMIN")
            .build()
        return InMemoryUserDetailsManager(andriodUser, admin)
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf{it.disable()}
            .authorizeHttpRequests {
                it.requestMatchers("/users/**").hasAnyRole("ANDROID", "ADMIN")
                it.requestMatchers("/tags/**").hasRole("ANDROID")
                it.requestMatchers("/giftstates/**").hasRole("ADMIN")
                it.anyRequest().permitAll()
            }
            .httpBasic {  }
        return http.build()
    }
}