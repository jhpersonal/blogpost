package com.jh.blogpost.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.HttpMethod.*
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableWebSecurity
class SecurityConfig(private val userService: JpaUserDetailsService) : WebSecurityConfigurerAdapter() {

    @Bean
    @Throws(Exception::class)
    override fun authenticationManager(): AuthenticationManager {
        return super.authenticationManager()
    }

    @Bean
    fun encoder(): PasswordEncoder {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder() # Throws error: There is no PasswordEncoder mapped for the id "null"
        return BCryptPasswordEncoder()
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder?) {

        auth!!.userDetailsService(userService)
            .passwordEncoder(encoder())
    }

    // Protecting the urls with a role-based access.
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests()
            .antMatchers("/actuator/**", "/v3/api-docs/**", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll()
            .antMatchers(GET, "/posts/{id}/author").hasAuthority("EDITOR")
            .antMatchers(DELETE, "/posts/{id}").hasAuthority("EDITOR")
            .antMatchers(GET,"/posts").hasAnyAuthority("EDITOR", "AUTHOR")               // (HttpMethod.GET, "/", "/publication").permitAll()
            .antMatchers(POST, "/posts").hasAnyAuthority("EDITOR","AUTHOR")
            .anyRequest().authenticated()
            .and()
            .formLogin()
                .defaultSuccessUrl("/")
                .permitAll()
//                .failureUrl("/login?error")
                .and()
            .logout()
                .logoutRequestMatcher(AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
//            .exceptionHandling()
//                .accessDeniedPage("/403")
//                .and()
            .httpBasic()


//            .and()
//            .csrf()
//            .disable()

        //            .sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()

    }


}