package com.jh.blogpost.user

import com.jh.blogpost.role.Role
import org.hibernate.annotations.common.util.impl.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.UsernameNotFoundException

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.authority.SimpleGrantedAuthority

import java.util.ArrayList

import org.springframework.security.core.GrantedAuthority


@Service
class UserService(): UserDetailsService {

    @Autowired
    lateinit var userRepository: UserRepository
    private val log = LoggerFactory.logger(UserService::class.java)

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): UserDetails {
        val user = userRepository.findByEmail(email) ?: throw UsernameNotFoundException(email)
        return User(user.email, user.password, getAuthorities(user.roles))
    }

    fun getAuthorities(roles: List<Role>?): Collection<GrantedAuthority> {
        val authorities = ArrayList<GrantedAuthority>()
        roles!!.forEach { role -> authorities.add(SimpleGrantedAuthority(role.name)) }
        return authorities

//        return roles.stream().map { role ->
//                log.debug("Granting Authority to user with role: $role")
//                SimpleGrantedAuthority(role.name)
//            }
//            .collect(Collectors.toList())
    }

}






// OLD CRUD User Service implementations
/*
    fun getUserByEmail(email: String): User? = userRepository.findByEmail(email)
    fun getUsersByName(name: String): List<User> = userRepository.findByName(name)

//    CRUD operations
    fun getUsers(): List<User> = userRepository.findAll()

    fun getUserById(userId: Long): User {
        var userDb: Optional<User> = userRepository.findById(userId)
        return try {
            if (userDb.isPresent)
                userRepository.getOne(userId)
            else
                throw Exception("Record not found with id: {userId}")
        } catch (ex: Exception) {
            throw Exception(ex.message)
        }
    }

    fun createUser(user: User): User = userRepository.save<User>(User(user.name, user.email))

    fun updateUser(userId: Long, user: User): User {
        var userDb: Optional<User> = userRepository.findById(userId)

        return try {
            if (userDb.isPresent) {
                val userUpdate: User = userRepository.getOne(userId)
//                userUpdate.id = userId
                userUpdate.name = user.name
                userUpdate.email = user.email
                userRepository.save(userUpdate)
            } else
               throw Exception("Record not found with id: {user.id}")
        } catch (ex: Exception) {
            throw Exception(ex.message)
        }
    }

    fun deleteUser(userId: Long) {
        var userDb: Optional<User> = userRepository.findById(userId)
        return try {
            if (userDb.isPresent)
                userRepository.deleteById(userId)
            else
                throw Exception("Record not found with id: {userId}")
        } catch (ex: Exception) {
            throw Exception(ex.message)
        }
    }

 */