package com.jh.blogpost.security

import com.jh.blogpost.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@ConditionalOnProperty(prefix="app.authentication.provider", value=["KEYCLOAK"])
@Service
class KeyCloakUserDetailsService: AuthenticationProvider {
    @Autowired
    lateinit var userRepository: UserRepository

    override fun loadUserByUsername(email: String): UserDetails {
        return userRepository.findByEmail("editor.mt.com") ?: throw UsernameNotFoundException(email)
//        return userRepository.findByEmail(email) ?: throw UsernameNotFoundException(email)
    }

}