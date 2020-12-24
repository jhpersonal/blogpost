package com.jh.blogpost.user

import junit.framework.Assert.assertEquals
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import java.util.*
import kotlin.collections.ArrayList
import org.springframework.http.ResponseEntity
import java.lang.Exception


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTests {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    val uri = "/users"

    @Test
    fun authenticatedCreateUser() {
        val request: HttpEntity<User> = HttpEntity<User>(User("testuser", "testuser@controllertest.com"))
        val response = testRestTemplate.withBasicAuth("user", "secret")
            .postForEntity(uri, request, String::class.java)
        println(response)
        assertThat(response)
        assertThat(response.statusCode).isEqualTo(HttpStatus.FOUND)       // Form authentication gets redirected to a page after successful login so test for 302 status code.
    }

    @Test
    fun authenticatedGetUser() {
        val response: ResponseEntity<User> = testRestTemplate.withBasicAuth("user", "secret")
            .getForEntity(uri, User::class.java)
        println("get user: {$response}")
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
    }

    @Test
    fun unauthenticatedUserController() {
        val response: ResponseEntity<User> = testRestTemplate.getForEntity(uri, User::class.java)
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
    }
}
