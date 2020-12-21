package com.jh.blogpost.publication

import com.jh.blogpost.integration.IntegrationCredentials
import com.jh.blogpost.integration.IntegrationCredentialsRepository
import com.jh.blogpost.post.BlogPost
import com.jh.blogpost.post.PostRepository
import com.jh.blogpost.user.User
import com.jh.blogpost.user.UserRepository
import org.assertj.core.api.Assertions
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureTestDatabase
class PublicationServiceTests {
    @Autowired
    lateinit var publicationRepository: PublicationRepository
    @Autowired
    lateinit var publicationService: PublicationService
    @Autowired
    lateinit var integrationCredentialsRepository: IntegrationCredentialsRepository
    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var postRepository: PostRepository

    @Test
    fun testPublicationRequest() {
        val user: User = userRepository.save(User("TestSparkle", "testsparkle@test.com"))
        val post: BlogPost = postRepository.save(BlogPost("test post title", "test post content", user))
        //integration creds save
        val integrationCredentials: IntegrationCredentials = integrationCredentialsRepository.save(IntegrationCredentials("testuser", "testpassword"))
        // Publication save
        val publicationType = PublicationType.Twitter
        publicationRepository.save(Publication(post, integrationCredentials, publicationType))

        // when
        publicationService.publicationRequest(post.id, publicationType.toString(), integrationCredentials.id)
        //then
//        Assert.assertNotNull(publicationRequest)
    }
}