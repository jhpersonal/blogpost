package com.jh.blogpost.security

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component


interface AuthenticationProvider: UserDetailsService {

//    @Autowired
//    lateinit var userService: JpaUserDetailsService
//
//    @ConditionalOnMissingBean(UserDetails::class)
//    fun defaultUserService(): JpaUserDetailsService {
//        return userService
//    }
//
//    override fun loadUserByUsername(email: String): UserDetails {
//        return defaultUserService().loadUserByUsername(email)
//    }
}