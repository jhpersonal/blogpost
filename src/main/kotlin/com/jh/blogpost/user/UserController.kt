package com.jh.blogpost.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

//@RestController
class UserController {
    @Autowired
    lateinit var userRepository: UserRepository

    // Get all Users
    @GetMapping("/users")
    fun getUsers(): List<User> = userRepository.findAll()

    // Get User by id
    @GetMapping("/users/{id}")
    fun getUserById(@PathVariable id: Long): User =
        userRepository.findById(id).orElseThrow {  Exception("Record not found with id: {id}") }

// TODO: VERIFY what's the better approach
//        return try {
//            ResponseEntity(userRepository.findById(id), HttpStatus.OK)
//        } catch(ex: Exception) {
//            ResponseEntity(HttpStatus.NOT_FOUND)
//        }


    // Get User by email
    @GetMapping(value=["/users/findbyemail/{email}"])
    fun getUserByEmail(@PathVariable email: String): User? = userRepository.findByEmail(email)

    // Get User by name
    @GetMapping(value=["/users/findbyname/{name}"])
    fun getUsersByName(@PathVariable name: String): List<User> = userRepository.findByName(name)

    // Create new User
    @PostMapping("/users")
    fun createUser(@RequestBody user: User): ResponseEntity<User> {
        return try {
            ResponseEntity.ok().body(userRepository.save(User(user.name, user.email)))
        } catch (ex: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR) //TODO: Pass exception message as body
        }
    }

    // Modify/Update existing User by id and request body
    @PutMapping("/users/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody user: User): User {
        var userDb: Optional<User> = userRepository.findById(id)

        if (!userDb.isPresent) throw Exception("Record not found with id: {user.id}")

        val userUpdate: User = userDb.get()
        userUpdate.name = user.name
        userUpdate.email = user.email
        return userRepository.save(userUpdate)
    }

    // Delete existing User by id
    @DeleteMapping("/users/{id}")
    fun deleteUser(@PathVariable id: Long) = userRepository.deleteById(id)

    // Delete all Users
    @DeleteMapping("/users")
    fun deleteUsers() = userRepository.deleteAll()



















/*
    @Autowired
    lateinit var userService: UserService

    // Get all Users
    @GetMapping("/users")
    fun getUsers(): List<User> = userService.getUsers()

    // Get User by id
    @GetMapping("/users/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<User> = ResponseEntity(userService.getUserById(id), HttpStatus.OK)

    // Get User by email
    @RequestMapping(value=["/users/findbyemail/{email}"])
    fun getUserByEmail(@PathVariable email: String): User? = userService.getUserByEmail(email)

    // Get User by name
    @RequestMapping(value=["/users/findbyname/{name}"])
    fun getUsersByName(@PathVariable name: String): List<User> = userService.getUsersByName(name)

    // Create new User
    @PostMapping("/users")
    fun createUser(@RequestBody user: User): ResponseEntity<User> =
        ResponseEntity.ok().body(userService.createUser(User(user.name, user.email)))

    // Modify/Update existing User by id and request body
    @PutMapping("/users/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody user: User): ResponseEntity<User> {
        return try {
            val userNew = userService.updateUser(id, User(user.name, user.email))
            ResponseEntity(userNew, HttpStatus.CREATED)
        } catch (e: Exception) {
            ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    // Delete existing User by id
    @DeleteMapping("/users/{id}")
    fun deleteUser(@PathVariable id: Long) = userService.deleteUser(id)
*/
}