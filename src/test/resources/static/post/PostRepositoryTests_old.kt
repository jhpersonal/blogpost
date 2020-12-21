package com.jh.blogpost.post

import com.jh.blogpost.user.User
import com.jh.blogpost.user.UserRepository
import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
class PostRepositoryTests_old {
    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var postRepository: PostRepository
    @Autowired
    lateinit var postService: PostService


    @BeforeAll
    fun setUp() {
        // given
        val user1: User = userRepository.save(User("Midnight", "midnight@test.com"))
//        userRepository.save(User("Starlight", "startlight@test.com"))
//        userRepository.save(User("Glimmer", "glimmer@test.com"))
//        userRepository.save(User("Sparkle", "sparkle@test.com"))
        val postDb: Post = Post("test post title", "test post content", user1)
        val post: Post = postRepository.save(postDb)
    }

    @AfterAll
    fun tearDown() {
        userRepository.deleteAll()
        postRepository.deleteAll()
    }

    @Test
    fun injectedPostComponentsAreNotNull() {
        Assertions.assertThat(userRepository).isNotNull
        Assertions.assertThat(postRepository).isNotNull
    }

    @Test
    fun whenFindAll_thenReturnPostList() {
        // when
        val posts:List<Post> = postRepository.findAll()
        //then
        org.junit.Assert.assertNotNull(posts)
        Assertions.assertThat(posts).hasSize(1);
    }

    @Test
    fun whenCreate_thenReturnPost() {
        // when
        val user: User = userRepository.save(User("Glow", "glow@test.com"))
        val postDb: Post = Post("test_title", "test post content", user)
        val post: Post = postService.createPost(postDb, 1)
        //then
        org.junit.Assert.assertNotNull(post)
//        org.junit.Assert.assertEquals(post.title, "test_title")
    }

    @Test
    fun whenUpdate_thenReturnUser() {
        // when
        val user: User = userRepository.save(User("Glow", "glow@test.com"))
        user.email = "glowModified@test.com"
        val userUpdate: User = userRepository.save(user)
        //then
        org.junit.Assert.assertNotNull(userUpdate)
        org.junit.Assert.assertEquals(user.email, "glowModified@test.com")
    }

    @Test
    fun whenDelete_thenReturnNoUser() {
        // when
        val user: User = userRepository.save(User("DeleteUser", "deleteUser@test.com"))
        val userDeleted = userRepository.deleteById(user.id)
        val users: List<User> = userRepository.findAll()
        print(userDeleted)
        //then
        Assertions.assertThat(users).extracting<String> { it.name }.doesNotContain("DeleteUser")
    }
}