package com.jh.blogpost.security

import java.net.URL

import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.beans.factory.annotation.Autowired


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InMemoryBasicAuthenticationTests {
    @Autowired
    lateinit var restTemplate: TestRestTemplate
    @LocalServerPort
    var port = 0
    lateinit var base: URL


    @Before
    fun setUp() {
        restTemplate = TestRestTemplate("editor", "password")
        base = URL("http://localhost:$port")
    }

    @Test
    fun whenLoggedUser_ThenSuccess() {
        val response: ResponseEntity<String> = restTemplate.getForEntity(base.toString(), String::class.java)
        assertEquals(HttpStatus.OK, response.statusCode)
        println(response.body)
    }

    @Test
    fun whenLoggedUser_Approach2_ThenSuccess() {
        restTemplate = TestRestTemplate()
        val response: ResponseEntity<String> = restTemplate
            .withBasicAuth("editor", "password")
            .getForEntity(base.toString(), String::class.java)
        assertEquals(HttpStatus.OK, response.statusCode)
        println(response.body)
    }

    @Test
    fun withWrongCredentials_thenUnauthorizedPage() {
        restTemplate = TestRestTemplate("editor", "wrongpassword")
        val response: ResponseEntity<String> = restTemplate.getForEntity(base.toString(), String::class.java)
        assertEquals(HttpStatus.UNAUTHORIZED, response.statusCode)
        println(response.statusCode)
    }

    @Test
    fun withoutAuthentication_thenUnauthorizedPage() {
        restTemplate = TestRestTemplate()
        val response: ResponseEntity<String> = restTemplate.getForEntity(base.toString(), String::class.java)
        assertEquals(HttpStatus.UNAUTHORIZED, response.statusCode)
        println(response.statusCode)
    }
}