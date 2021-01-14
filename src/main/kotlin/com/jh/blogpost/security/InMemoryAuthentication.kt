package com.jh.blogpost.security

import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.factory.PasswordEncoderFactories

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.AuthenticationFilter

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
            .roles("ADMIN", "USER")
    }

    override fun configure(http: HttpSecurity) {
        //This tells Spring Security to authorize all requests
        http
            .httpBasic()
            .and()
                .authorizeRequests()
                .antMatchers("/v3/api-docs", "/swagger*/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()   // disable Spring Security built-in cross-site scripting protection. protects against common exploits like CSRF.
                .formLogin().disable()  // disable default login form


//            .sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)     // Spring Security will never create an HttpSession and it will never use it to obtain the SecurityContext
//            .and()
////        http.headers().frameOptions().disable()
//            .logout().permitAll()

        //disable csrf to just certain paths : .csrf().ignoringAntMatchers("/api/**")

        //NOTE: there’s no explicit logout with HTTP basic authentication. To force logout, you must exit the browser.
        // BCrypt is a strong hashing algorithm recommended by Spring Security.
    }

}


