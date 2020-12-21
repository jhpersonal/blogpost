package com.jh.blogpost.post

import com.jh.blogpost.user.User
import com.jh.blogpost.user.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDateTime

@RunWith(SpringRunner::class)
@DataJpaTest
//@SpringBootTest
//@AutoConfigureTestDatabase
 class PostRepositoryTests {

    @Autowired
    lateinit var userRepository: UserRepository
   @Autowired
   lateinit var postRepository: PostRepository

    @Before
    internal fun setUp() {
        // given
        val midnightUser = User("Midnight", "midnight@test.com")
        val starlightUser =  User("Starlight", "startlight@test.com")
        userRepository.saveAll(listOf(midnightUser, starlightUser))

        val blankPost = BlogPost("blank post title", "blank post content", starlightUser)
        val blogPost = BlogPost("blog post title", "blog post content", midnightUser)
        val howtoPost = HowToPost("how-to post title", "how-to post content", starlightUser)

        postRepository.saveAll(listOf(blankPost, blogPost, howtoPost))
    }

    // 'getPosts' should retrieve empty list if repository doesn't contain entities
    @Test
    fun testGetPosts() {
        // when
        val posts: List<Post> = postRepository.findAll()
        // then
        assertThat(posts).size().isNotZero
        assertThat(posts).extracting<String> { it.title }.contains("blank post title")
    }

    @Test
    fun testCreateBlogPost() {
        // when
        val glimmerUser: User = userRepository.save(User("Glimmer", "glimmer@test.com"))
        val blogPost1: BlogPost = BlogPost("post_title", "JUnit Post Service get posts testing content", glimmerUser)
        val savedPost = postRepository.save(blogPost1)

        //then
        assert(savedPost is BlogPost)
    }

    @Test
    fun testFindByAuthor()
    {
        val midnightUser: User? = userRepository.findByEmail("midnight@test.com")
        if (midnightUser != null) {
            val blogPost: List<Post> = postRepository.findByAuthor(midnightUser)
            assertThat(blogPost).isNotEmpty
            assertThat(blogPost).hasSize(1)
        }
    }

    @Test
    fun testFindPostsByAuthorAndCreatedAt() {
        val createdDate = LocalDateTime.now().plusDays(1)   //plusHours(4)
        val user: User = userRepository.save(User("post_author_date", "post_author_date@test.com"))
        user.createdAt = createdDate
        val post = BlankPost("compare_title_by_authorAndDate", "filter posts by author and created at", user)

        val savedPost = postRepository.save(post)
        println(savedPost.createdAt)
        println(user.createdAt.minusHours(2))
        val searchPosts: List<Post> = postRepository.findByAuthorAndCreatedAtBefore(user, user.createdAt.minusHours(2))
        //         then
        assertThat(searchPosts).isNotEmpty
    }

    @Test
    fun testUpdatedPost() {
        val post: Post = postRepository.findAll().first()
        val origTitle = post.title
        val modifiedPost: BlogPost = BlogPost("modified title", post.content, post.author)
        modifiedPost.id = post.id
        val savedPost: Post = postRepository.save(modifiedPost)
        //then
        assertThat(savedPost).extracting<String> { it.title }.isEqualTo("modified title")
    }

    @Test
    fun testDeletePost() {
        // when
        val post: Post = postRepository.findAll().first()
        postRepository.deleteById(post.id)
        val posts: List<Post> = postRepository.findAll()

        //then
        assertThat(posts).extracting<String> { it.title }.doesNotContain(post.title)
    }


}