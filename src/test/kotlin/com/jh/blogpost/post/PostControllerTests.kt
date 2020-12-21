package com.jh.blogpost.post

import com.jh.blogpost.user.User
import junit.framework.Assert.assertEquals
import net.bytebuddy.agent.VirtualMachine
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

import org.junit.runner.RunWith
import net.bytebuddy.agent.VirtualMachine.ForHotSpot.Connection.ForJnaWindowsNamedPipe.Factory.LIBRARY_NAME
import org.junit.Test
import org.springframework.boot.test.web.client.postForEntity


import org.springframework.http.ResponseEntity

import org.springframework.http.HttpEntity





@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class PostControllerTests {
    @Autowired
    lateinit var template: TestRestTemplate

    private val POST_ENDPOINT = "http://localhost:8081/posts/"
    private val USER_ENDPOINT = "http://localhost:8081/users/"
    private val POST_TITLE = "Controller Test Title"
    private val POST_CONTENT = "Controller Test Content"
    private val USER_NAME = "Brigette Ann"
    private val USER_EMAIL = "brigette_ann@controllertest.com"


    @Test
    fun whenSaveOneToManyRelationship_thenCorrect() {
        val user = User(USER_NAME, USER_EMAIL)
        template.postForEntity(USER_ENDPOINT, user, User::class.java)

//        val post1 = Post(POST_TITLE, POST_CONTENT, user)
//        template.postForEntity(POST_ENDPOINT, post1, Post::class.java)

        val userGetResponse = template.getForEntity("$POST_ENDPOINT/1/users", User::class.java)
        assertEquals(USER_NAME, userGetResponse.body?.name)


//        val library = Library(VirtualMachine.ForHotSpot.Connection.ForJnaWindowsNamedPipe.Factory.LIBRARY_NAME)
//        template!!.postForEntity(LIBRARY_ENDPOINT, library, Library::class.java)
//        val book1 = Book("Dune")
//        template.postForEntity(BOOK_ENDPOINT, book1, Book::class.java)
//        val book2 = Book("1984")
//        template.postForEntity(BOOK_ENDPOINT, book2, Book::class.java)
//        val requestHeaders = HttpHeaders()
//        requestHeaders.add("Content-Type", "text/uri-list")
//        val bookHttpEntity = HttpEntity("$LIBRARY_ENDPOINT/1", requestHeaders)
//        template.exchange(
//            "$BOOK_ENDPOINT/1/library",
//            HttpMethod.PUT, bookHttpEntity, String::class.java
//        )
//        template.exchange(
//            "$BOOK_ENDPOINT/2/library",
//            HttpMethod.PUT, bookHttpEntity, String::class.java
//        )
//        val libraryGetResponse = template.getForEntity(
//            "$BOOK_ENDPOINT/1/library",
//            Library::class.java
//        )
//        assertEquals(
//            "library is incorrect",
//            libraryGetResponse.body.getName(),
//            VirtualMachine.ForHotSpot.Connection.ForJnaWindowsNamedPipe.Factory.LIBRARY_NAME
//        )
    }
}
