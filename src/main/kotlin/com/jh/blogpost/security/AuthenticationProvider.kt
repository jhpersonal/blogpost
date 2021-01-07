package com.jh.blogpost.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

//@Service
//class AuthenticationProvider(): UserDetailsService {
////    @Autowired
////    lateinit var userService: JpaUserDetailsService
////
////    @ConditionalOnMissingBean(UserDetails::class)
////    fun defaultUserService(): JpaUserDetailsService {
////        return userService
////    }
////
////    override fun loadUserByUsername(email: String): UserDetails {
////        return defaultUserService().loadUserByUsername(email)
////    }
//}