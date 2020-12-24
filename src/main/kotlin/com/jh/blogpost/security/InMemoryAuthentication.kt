package com.jh.blogpost.security

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.crypto.factory.PasswordEncoderFactories

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import java.lang.Exception

@Configuration
@EnableWebSecurity
class InMemoryAuthentication: WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        //This tells Spring Security to authorize all requests
        http.httpBasic()
            .and()
            .authorizeRequests()
            .antMatchers("/v3/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll()
            .antMatchers(HttpMethod.GET, "/posts/{id}/author").hasRole("EDITOR")
            .antMatchers(HttpMethod.DELETE, "/posts/{id}").hasRole("EDITOR")
            .antMatchers(HttpMethod.GET,"/posts").hasAnyRole("EDITOR", "AUTHOR")               // (HttpMethod.GET, "/", "/publication").permitAll()
            .antMatchers(HttpMethod.POST, "/posts").hasAnyRole("EDITOR","AUTHOR")
                            // role-based authorization
            .anyRequest().authenticated()    //.permitAll()
//            .and().logout().permitAll().logoutRequestMatcher( AntPathRequestMatcher("/logout", HttpMethod.GET.toString())).invalidateHttpSession(true)
            .and()
            .csrf().disable()   // disable Spring Security built-in cross-site scripting protection.
            .formLogin().disable()  // disable default login form

        //NOTE: there’s no explicit logout with HTTP basic authentication. To force logout, you must exit the browser.
        // BCrypt is a strong hashing algorithm recommended by Spring Security.
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        val encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

        // This code sets up a user store in memory.
        auth.inMemoryAuthentication()
            .withUser("editor").password(encoder.encode("pass")).roles("EDITOR")
            .and()
            .withUser("author").password(encoder.encode("pass")).roles("AUTHOR")
    }
}





//    @Bean
//    fun encoder(): PasswordEncoder {
//        return BCryptPasswordEncoder()
//    }
