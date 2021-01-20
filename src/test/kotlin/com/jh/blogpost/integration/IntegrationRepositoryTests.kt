package com.jh.blogpost.integration

import com.jh.blogpost.facebook.TwitterCredentials
import com.jh.blogpost.facebook.TwitterIntegration
import com.jh.blogpost.post.BlogPost
import com.jh.blogpost.post.PostRepository
import com.jh.blogpost.publication.Publication
import com.jh.blogpost.publication.PublicationRepository
import com.jh.blogpost.user.User
import com.jh.blogpost.user.UserRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
class IntegrationRepositoryTests {

    @Autowired
    lateinit var integrationRepository: IntegrationRepository
    @Autowired
    lateinit var integrationCredentialsRepository: IntegrationCredentialsRepository
    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var postRepository: PostRepository

    @Test
    fun TestCreateIntegration() {
        val user: User = userRepository.save(User("Sparkle", "sparkle@test.com"))
        val post: BlogPost = postRepository.save(BlogPost("orig_title", "original content", user))
        //integration creds save
        val integrationCred: TwitterCredentials = integrationCredentialsRepository.save(TwitterCredentials("testtwuser", "testtwpassword"))
        val integration: TwitterIntegration = integrationRepository.save(TwitterIntegration("integrate with twitter test", integrationCred))
        //then
        Assert.assertNotNull(integration)
    }

}