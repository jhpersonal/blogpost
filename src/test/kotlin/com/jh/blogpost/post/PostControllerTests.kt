package com.jh.blogpost.post

import junit.framework.Assert.assertEquals
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.http.ResponseEntity
import org.springframework.web.server.ResponseStatusException
import java.lang.Exception
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import java.net.URI
import org.springframework.hateoas.config.HypermediaWebClientConfigurer

import org.springframework.boot.web.reactive.function.client.WebClientCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.hateoas.config.HypermediaRestTemplateConfigurer

import org.springframework.boot.web.client.RestTemplateCustomizer
import org.springframework.web.client.RestTemplate


@Bean
fun restTemplateCustomizer(configurer: HypermediaRestTemplateConfigurer): RestTemplateCustomizer {
    return RestTemplateCustomizer { restTemplate: RestTemplate -> configurer.registerHypermediaTypes(restTemplate) }
}

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("local")
class PostControllerTests {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate
    val uri = "/posts"

    //GET
    @Test
    fun authenticatedGetPostsShouldSucceedWith200() {   //EDITOR ROLE
//        saveOneUser()
        val result: ResponseEntity<String> = testRestTemplate.withBasicAuth("editor", "pass").getForEntity(uri, String::class.java)
        println(result)
        assertEquals(HttpStatus.OK, result.statusCode)
    }

    @Test
    @Throws(Exception::class)
    fun unauthenticatedGetUserShouldFailWith401() {
        val result: ResponseEntity<String> = testRestTemplate.getForEntity(uri, String::class.java)
        assertEquals(HttpStatus.UNAUTHORIZED, result.statusCode)
    }
   /*
    //POST
    @Test
    fun authenticateCreatePostsShouldSucceedWith200() {     //EDITOR ROLE
        val user = User("testuser", "testuser@controllertest.com")
        val blogPost = BlogPost("blog post title", "blog post content", user)
        val result: ResponseEntity<BlogPost> = testRestTemplate.withBasicAuth("editor", "pass")
            .postForEntity(uri, blogPost, BlogPost::class.java)
        println(result)
        assertEquals(HttpStatus.CREATED, result.statusCode)
    }

    //DELETE
    @Test
    fun authenticateDeletePostsShouldSucceedWith200() {     //EDITOR ROLE
        val user = User("testuser", "testuser@controllertest.com")
        val blogPost = BlogPost("blog post title", "blog post content", user)
        val result: ResponseEntity<BlogPost> = testRestTemplate.withBasicAuth("editor", "pass")
            .postForEntity(uri, blogPost, BlogPost::class.java)
        assertEquals(HttpStatus.CREATED, result.statusCode)

        println("created post id: ${result.body?.id}")
        val postByIdUri = "{uri}/${result.body?.id}"
        testRestTemplate.delete(postByIdUri)

        try {
            testRestTemplate.withBasicAuth("editor", "pass").getForEntity(postByIdUri, BlogPost::class.java)
        } catch (ex: ResponseStatusException) {
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, ex.rawStatusCode)
        }
    }

    //GET Posts by author
    @Test
    fun authenticateGetPostByAuthorShouldSucceedWith200() {     //EDITOR ROLE
        val user = User("postbyauthor", "postbyauthor@controllertest.com")
        val blankPost = BlankPost("blank post title", "blank post content", user)
        val result: ResponseEntity<BlankPost> = testRestTemplate.withBasicAuth("editor", "pass")
            .postForEntity(uri, blankPost, BlankPost::class.java)
        assertEquals(HttpStatus.CREATED, result.statusCode)

        println("created post id: $result")
        val postByIdUri = "$uri/${result.body?.id}/author"
        testRestTemplate.withBasicAuth("editor", "pass").getForEntity(postByIdUri, BlankPost::class.java)
        assertEquals(HttpStatus.OK, result.statusCode)
    }
*/

}

//    val testRestTemplateWithAuthorAuth = TestRestTemplate("author", "pass")
//    var testRestTemplateWithClientOptions = TestRestTemplate("editor","pass", TestRestTemplate.HttpClientOption.ENABLE_COOKIES)     // ENABLE_REDIRECTS
//    val uri = "/posts"
//    val testRestTemplateWithEditorAuth = TestRestTemplate("editor", "pass")
//
//    @LocalServerPort
//    var randomServerPort = 0
//
//    val baseUrl = "http://localhost:$randomServerPort/posts/"
//    var baseUri: URI = URI(baseUrl)