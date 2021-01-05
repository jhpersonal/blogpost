package com.jh.blogpost.security

import com.jh.blogpost.role.Role
import com.jh.blogpost.role.RoleRepository
import com.jh.blogpost.user.User
import com.jh.blogpost.user.UserRepository
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import java.lang.Exception
import java.net.MalformedURLException
import java.net.URL

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
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


    @Before
    @Throws(MalformedURLException::class)
    fun setUp() {
        restTemplate = TestRestTemplate("editor@mt.com", "password")
        base = URL("http://localhost:$port")
    }

    @Test
    fun whenLoggedUser_ThenSuccess()
    {
        val response: ResponseEntity<String> = restTemplate.getForEntity("${base.toString()}/users/1", String::class.java)
        assertEquals(HttpStatus.OK, response.statusCode)
//        println(response.body)
        assertThat(response.body).contains("editor@mt.com")
    }

    @Test
    @Throws(Exception::class)
    fun withoutAuthentication_thenUnauthorizedPage() {
        restTemplate = TestRestTemplate()
        val response: ResponseEntity<User> = restTemplate.getForEntity("${base.toString()}/users/1", User::class.java)
        assertEquals(HttpStatus.UNAUTHORIZED, response.statusCode)
    }

    @Test
    @Throws(Exception::class)
    fun withWrongCredentials_thenUnauthorizedPage() {
        restTemplate = TestRestTemplate("user", "wrongpassword")
        val response: ResponseEntity<User> = restTemplate.getForEntity("${base.toString()}/users/1", User::class.java)
        assertEquals(HttpStatus.UNAUTHORIZED, response.statusCode)
    }

    @Test
//    @WithMockUser(username = "editor@mt.com", authorities = ["EDITOR"])
    fun testSecurity() {
//        def url = "http://localhost:9090"

//        val roleEntity = Role(name="TEST")
//        roleEntity.id = 99
//        val role = roleRepository.saveAll(listOf(roleEntity))
//        val user = userRepository.save(User("test", "test@test.com", "password", role))
        val users = userRepository.findAll()
        assertNotNull(users)
        assertThat(users).size().isNotZero

//        val users: MutableList<User> = mutableListOf<User>()
//        users.add(user)
//        roleRepository.save("editor")
//        val role = roleRepository.saveAll(listOf(Role("EDITOR"), Role("AUTHOR")))
//        user.roles = role
//        userRepository.save(user)

//        userService.loadUserByUsername(user.email)
    }

}