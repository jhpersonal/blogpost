package com.jh.blogpost.security

import com.jh.blogpost.role.Role
import com.jh.blogpost.role.RoleRepository
import com.jh.blogpost.user.User
import com.jh.blogpost.user.UserRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit4.SpringRunner
import java.net.MalformedURLException
import java.net.URL

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JpaSecurityAuthenticationTests {
    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var roleRepository: RoleRepository
    @Autowired
    lateinit var userService: JpaUserDetailsService
    @Autowired
    lateinit var restTemplate: TestRestTemplate
    @LocalServerPort
    var port = 0
    lateinit var base: URL
    val userEmail = "test@test.com"


    @Before
    @Throws(MalformedURLException::class)
    fun setUp() {
        restTemplate = TestRestTemplate("editor", "password")
        base = URL("http://localhost:$port")
    }

    @Test
    fun testSecurity() {
//        def url = "http://localhost:9090"
        val users: MutableList<User> = mutableListOf<User>()
        val user = userRepository.save(User("test", "test@test.com", "password"))
        users.add(user)
//        roleRepository.save("editor")
        val role = roleRepository.saveAll(listOf(Role("EDITOR"), Role("AUTHOR")))
        user.roles = role
        userRepository.save(user)

        userService.loadUserByUsername(user.email)
    }

}