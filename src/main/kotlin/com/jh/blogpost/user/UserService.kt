package com.jh.blogpost.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserService() {

    @Autowired
    lateinit var userRepository: UserRepository

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


}