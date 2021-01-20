package com.jh.blogpost.publication

import com.jh.blogpost.facebook.FacebookCredentials
import com.jh.blogpost.facebook.FacebookIntegration
import com.jh.blogpost.facebook.TwitterCredentials
import com.jh.blogpost.facebook.TwitterIntegration
import com.jh.blogpost.integration.IntegrationCredentials
import com.jh.blogpost.integration.IntegrationCredentialsRepository
import com.jh.blogpost.integration.IntegrationRepository
import com.jh.blogpost.post.BlogPost
import com.jh.blogpost.post.PostRepository
import com.jh.blogpost.user.User
import com.jh.blogpost.user.UserRepository
import org.assertj.core.api.Assertions
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

//@SpringBootTest
//@AutoConfigureTestDatabase
@RunWith(SpringRunner::class)
@DataJpaTest
class PublicationRepositoryTests {
    @Autowired
    lateinit var publicationRepository: PublicationRepository
    @Autowired
    lateinit var integrationRepository: IntegrationRepository
    @Autowired
    lateinit var integrationCredentialsRepository: IntegrationCredentialsRepository
    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var postRepository: PostRepository


    @Before
    internal fun setUp() {
        val user: User = userRepository.save(User("Sparkle", "sparkle@test.com"))
        val post: BlogPost = postRepository.save(BlogPost("orig_title", "original content", user))
        //integration creds save
        val integrationCred: TwitterCredentials = integrationCredentialsRepository.save(TwitterCredentials("testuser", "testpassword"))
        val integration: TwitterIntegration = integrationRepository.save(TwitterIntegration("integrate with twitter test", integrationCred))
//      Publication save
       publicationRepository.save(Publication(post, integration))
    }

    @Test
    fun testGetAllPublications() {
        // when
        val publications: List<Publication> = publicationRepository.findAll()
        //then
        Assert.assertNotNull(publications)
        Assertions.assertThat(publications).size().isNotZero;
    }

//    @Test
//    fun testFindByPublicationPost() {
////      when
//        val publications: List<Publication> = publicationRepository.findAll()
//        val publication: Publication = publications.first()
//        val postSearch: List<Publication> = publicationRepository.findByPublicationPost(publication.publicationPost)
//        //then
//        Assert.assertNotNull(postSearch)
//        Assertions.assertThat(postSearch).size().isNotZero;
//    }

    @Test
    fun testCreatePublication() {
        // when
        val user: User = userRepository.save(User("Starlight", "startlight@test.com"))
        val post: BlogPost = postRepository.save(BlogPost("create_title", "create content", user))
//        integration creds save
        val integrationCred: FacebookCredentials = integrationCredentialsRepository.save(FacebookCredentials("fbUser", "fbPassword"))

        val integration: FacebookIntegration = integrationRepository.save(FacebookIntegration("integrate with facebook", integrationCred))
//      Publication save
        val publication = publicationRepository.save(Publication(post, integration))
//        then
        Assert.assertNotNull(publication)
//        Assert.assertEquals(user.email, "startlight@test.com")
    }

    @Test
    fun testUpdatePublication() {
//        when
        val user: User = userRepository.save(User("Glimmer", "glimmer@test.com"))
        val post: BlogPost = postRepository.save(BlogPost("create_new_title", "create new content", user))
//        integration creds save
        val integrationCred: TwitterCredentials = integrationCredentialsRepository.save(TwitterCredentials("tw_user", "twPassword"))
        val integration: TwitterIntegration = integrationRepository.save(TwitterIntegration("twitter 3rd party integration test", integrationCred))
//      Publication save
        val publication: Publication = publicationRepository.save(Publication( post, integration))
//        then
        val publicationUpdate: Publication = publicationRepository.save(publication)
//      then
        Assert.assertNotNull(publicationUpdate)
    }

    @Test
    fun testDeletePublication() {
//       setUpPublications()
       val publication: Publication = publicationRepository.findAll().first()

        // when
        publicationRepository.deleteById(publication.id)
        val publicationsAfterDelete: Optional<Publication> = publicationRepository.findById(publication.id)

        //then
        Assertions.assertThat(publicationsAfterDelete.isPresent).isFalse
    }

//    @After
//    fun tearDown() {
//        userRepository.deleteAll()
//        postRepository.deleteAll()
//        integrationCredentialsRepository.deleteAll()
//        integrationRepository.deleteAll()
//        publicationRepository.deleteAll()
//    }
}

