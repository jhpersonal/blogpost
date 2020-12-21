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
@ActiveProfiles("local")
class UserControllerTests {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    class UsersList : MutableList<User> by ArrayList()
    val userFiona = User("Fiona","fiona@controllertest.com")
    val uri = "/users"

    @Test
    fun createUser() {
//        testRestTemplate.postForLocation(uri, HttpEntity(userFiona))
        val request: HttpEntity<User> =  HttpEntity<User>(User("testuser", "testuser@controllertest.com"))
        val user = testRestTemplate.withBasicAuth("user", "secret")
            .postForEntity(uri, request, String::class.java)
        println(user)
        assertThat(user) //.isNotNull
//        assertThat(user.name).isEqualTo("testuser")
    }

    @Test
    fun testSecurityGetUser() {
//        testRestTemplate.postForLocation(uri, HttpEntity(userFiona))
        val response = testRestTemplate.getForEntity(uri, User::class.java)
        val responseBody = response.body
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
    }

    @Test
    @Throws(Exception::class)
    fun givenAuthRequestOnPrivateService_shouldSucceedWith200() {
        val result: ResponseEntity<String> = testRestTemplate.withBasicAuth("user", "secret")
            .getForEntity(uri, String::class.java)
        assertEquals(HttpStatus.OK, result.statusCode)
    }
    @Test
    fun indexShouldReturnSingleUser() {
//        testRestTemplate.postForLocation(uri, HttpEntity(userFiona))
        val response = testRestTemplate.getForEntity(uri, User::class.java)
        val responseBody = response.body
        println(responseBody)
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseBody).isNotNull  //.isNotEmpty
//        assertThat(responseBody?.name).isEqualTo("Fiona")
//        assertThat(responseBody?.email).isEqualTo("fiona@controllertest.com")



//        assertThat(response.body?.size).isEqualTo(1)
//        assertThat(response.body?.get(0)?.name).isEqualTo("Fiona")
//        assertThat(response.body?.get(0)?.email).isEqualTo("fiona@controllertest.com")
    }

    @Test
    fun getUserShouldReturnUserById() {
        val allUsersResponse = testRestTemplate.getForEntity(uri, User::class.java)

        val id = allUsersResponse.body?.id  // get(0)?.id
        assertThat(id).isNotNull
        val getUserResponse = testRestTemplate.getForEntity("/users/$id", User::class.java)
        print(getUserResponse.body)
        assertThat(getUserResponse.body).hasFieldOrPropertyWithValue("name","Fiona")  //.isSameAs(userFiona, "name", "email")
    }

    @Test
    fun shouldReturnNotFoundIfReceivedWrongId() {
        val response = testRestTemplate.getForEntity("/users/${UUID.randomUUID()}", Any::class.java)
        assertThat(response.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
    }

    @Test
    fun errorHandlingWithTestRestTemplate() {
        val entity = testRestTemplate.getForEntity("/users/search/findByEmail?email=fiona@controllertest.com", User::class.java)
        val entityObj = testRestTemplate.getForEntity("/users/search/findByEmail?email=fiona@controllertest.com", Object::class.java)
        assertThat(entity.statusCode).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR)
    }
}

/*
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("local")
class UserControllerTestsWithActiveProfiles {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    class UsersList : MutableList<User> by ArrayList()

    val userFiona = User("Fiona", "fiona@controllertest.com")

//    var formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
//    val userFiona = User("Fiona","fiona@controllertest.com",
//        LocalDateTime.parse("2020-12-14 15:45:15", formatter),
//        LocalDateTime.parse("2020-12-14 15:45:15",formatter), 1)

    @Test
    fun testShouldGetAllUsers() {
        val response = testRestTemplate.getForEntity("/users", UsersList::class.java)
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).isNotEmpty
        assertThat(response.body?.size).isEqualTo(1)
        print(response.body?.get(0)?.name)

        assertThat(response.body?.get(0)?.name).isEqualTo("jaya")
}

    @Test
    fun testShouldFindUserByEmail() {
        val result = testRestTemplate.getForEntity("/users/search/findByEmail?email=fiona@controllertest.com",
            UsersList::class.java)
        Assert.assertNotNull(result)
        Assert.assertEquals(HttpStatus.OK, result.statusCode)
        println(result.body)
        val users = result.body?.get(0)
//        Assert.assertEquals(2, users.size)
//        Assert.assertNotNull(users.find { it.name == "jaya" })
//        Assert.assertNotNull(users.find { it.email == "jaya@mt.com" })
    }
}
*/





//        val result = testRestTemplate.getForEntity("/users", String::class.java)
//        Assert.assertNotNull(result)
//        Assert.assertEquals(HttpStatus.OK, result.statusCode)
//        println(result.body)
//        val users: String? = result.body
//        Assert.assertNotNull(users.find { it.name == "Jaya" })

//        val response: List<User> = testRestTemplate.getForObject("http://localhost:8080/users", UsersList::class.java)
//        val users: List<User> = response.body.   getUsers()