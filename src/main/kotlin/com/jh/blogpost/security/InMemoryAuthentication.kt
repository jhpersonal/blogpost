package com.jh.blogpost.security

import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.factory.PasswordEncoderFactories

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
class InMemoryAuthentication: WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        val encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

        // This code sets up a user store in memory.
        auth.inMemoryAuthentication()
            .withUser("editor")
            .password(encoder.encode("password"))
            .roles("USER")
            .and()

            .withUser("author")
            .password(encoder.encode("password"))
            .roles("ADMIN")
    }

    override fun configure(http: HttpSecurity) {
        //This tells Spring Security to authorize all requests
        http
            .httpBasic()
            .and()
                .authorizeRequests()
                .antMatchers("/v3/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .csrf().disable()   // disable Spring Security built-in cross-site scripting protection.
            .formLogin().disable()  // disable default login form

        //NOTE: there’s no explicit logout with HTTP basic authentication. To force logout, you must exit the browser.
        // BCrypt is a strong hashing algorithm recommended by Spring Security.
    }

}


