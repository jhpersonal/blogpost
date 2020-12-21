package com.jh.blogpost.post

import com.jh.blogpost.user.User
import com.jh.blogpost.user.UserRepository
import com.jh.blogpost.user.UserService
import junit.framework.Assert
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.JUnitSoftAssertions
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
//@DataJpaTest
@SpringBootTest
@AutoConfigureTestDatabase
 class PostServiceTests {

    @Autowired(required = true)
    lateinit var postService: PostService

    @Autowired
    lateinit var userRepository: UserRepository
   @Autowired
   lateinit var postRepository: PostRepository

    @BeforeAll
    internal fun setUp() {
        // given
        val midnightUser = User("Midnight", "midnight@test.com")
        val starlightUser =  User("Starlight", "startlight@test.com")
        userRepository.saveAll(listOf(midnightUser, starlightUser))

        val blankPost = BlogPost("blank post title", "blog post content", starlightUser)
        val blogPost = BlogPost("blog post title", "blog post content", midnightUser)
        val howtoPost = HowToPost("blog post title", "blog post content", starlightUser)

        postRepository.saveAll(listOf(blankPost, blogPost, howtoPost))
    }

    // 'getPosts' should retrieve empty list if repository doesn't contain entities
    @Test
    fun getPosts_thenCompareData() {
        // when
        val posts: List<Post> = postRepository.findAll()
        // then
        assertThat(posts).extracting<String> { it.title }.contains("blank post title")
    }
//
//    @Test
//    fun whenCreateBlogPost_thenReturnBlogPost() {
//        // when
//        val user: User = userRepository.save(User("Starlight", "startlight@test.com"))
//        val postDb: BlogPost = BlogPost("post_title", "JUnit Post Service get posts testing content", user)
//        val savedPost = postService.createPost(postDb)
//        val posts: List<Post> = postService.getPosts()
//
//        //then
//        assertThat(posts).isNotEmpty
//        assertThat(posts).size().isNotZero
//        println(savedPost is HowToPost)
////        assert(savedPost is HowToPost)
//        assert(savedPost is BlogPost)
//    }
//
//    @Test
//    fun whenUpdateContent_thenReturnUpdatedPost() {
//        val user: User = userRepository.save(User("Sparkle", "sparkle@test.com"))
//        val postDb: Post = Post("orig_title", "original content", user)
//        val savedPost: Post = postService.createPost(postDb, user.id)
//        val modifiedPostData: Post = Post("orig_title", "modified content", user)
//        val modifiedPost = postService.updatePost(modifiedPostData, savedPost.id)
//
//        //then
//        assertThat(modifiedPost).extracting<String> { it.content }.isEqualTo("modified content")
//    }
//
//    @Test
//    fun whenDelete_thenReturnNoPost() {
//        // when
//        val user: User = userRepository.save(User("DeleteUser", "deleteUser@test.com"))
//        val postDb: Post = Post("delete_title", "delete content", user)
//        val savedPost: Post = postService.createPost(postDb, user.id)
//        postService.deletePost(savedPost.id)
//        val posts: List<Post> = postService.getPosts()
//
//        //then
//        assertThat(posts).extracting<String> { it.title }.doesNotContain("delete_title")
//    }
//
//    @Test
//    fun getPostsByAuthor_thenCompareData() {
//        val user: User = userRepository.save(User("post_author", "post_author@test.com"))
//        val postDb: Post = Post("compare_title_by_author", "filter posts by author", user)
//        postService.createPost(postDb, user.id)
//        val posts: List<Post> = postService.findPostsByAuthor(user.id)
//
////         then
//        assertThat(posts).extracting<String> { it.author.name }.contains("post_author")
//    }
//
//    @Test
//    fun getPostsByAuthorAndCreatedAt_thenCompareData() {
//        val user: User = userRepository.save(User("post_author_date", "post_author_date@test.com"))
//        val postDb: Post = Post("compare_title_by_authorAndDate", "filter posts by author and created at", user)
//        postService.createPost(postDb, user.id)
//
//        val posts: List<Post> = postService.findPostByAuthorAndCreatedDate(user.id, 2)
//        //         then
//        assertThat(posts).isNullOrEmpty()
//    }

//    @AfterAll
//    fun TeardownPosts() {
//        // given
//        userRepository.deleteAll()
//        postRepository.deleteAll()
//    }
}