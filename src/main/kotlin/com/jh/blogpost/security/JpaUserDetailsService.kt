package com.jh.blogpost.security

import com.jh.blogpost.user.UserRepository
import org.hibernate.annotations.common.util.impl.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.factory.PasswordEncoderFactories

@ConditionalOnProperty(name = ["app.authentication.provider"], havingValue="JPA", matchIfMissing=true)
@Service
class JpaUserDetailsService: AuthenticationProvider {

    @Autowired
    lateinit var userRepository: UserRepository

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): UserDetails {
//        val encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()
//        println(encoder.encode("password"))
        return  userRepository.findByEmail(email) ?: throw UsernameNotFoundException(email)

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