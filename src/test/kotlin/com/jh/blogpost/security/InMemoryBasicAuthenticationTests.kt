package com.jh.blogpost.security

import java.io.IOException
import java.net.MalformedURLException
import java.lang.Exception
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
    @Throws(MalformedURLException::class)
    fun setUp() {
        restTemplate = TestRestTemplate("user", "password")
        base = URL("http://localhost:$port")
    }

    @Test
    @Throws(IllegalStateException::class, IOException::class)
    fun whenLoggedUser_ThenSuccess() {
        val response: ResponseEntity<String> = restTemplate.getForEntity(base.toString(), String::class.java)
        assertEquals(HttpStatus.OK, response.statusCode)
        println(response.body)
    }

    @Test
    @Throws(Exception::class)
    fun withWrongCredentials_thenUnauthorizedPage() {
        restTemplate = TestRestTemplate("user", "wrongpassword")
        val response: ResponseEntity<String> = restTemplate.getForEntity(base.toString(), String::class.java)
        assertEquals(HttpStatus.UNAUTHORIZED, response.statusCode)
    }

    @Test
    @Throws(Exception::class)
    fun withoutAuthentication_thenUnauthorizedPage() {
        restTemplate = TestRestTemplate()
        val response: ResponseEntity<String> = restTemplate.getForEntity(base.toString(), String::class.java)
        assertEquals(HttpStatus.UNAUTHORIZED, response.statusCode)
    }
}