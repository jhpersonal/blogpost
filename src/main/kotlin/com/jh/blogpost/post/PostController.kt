package com.jh.blogpost.post

import com.jh.blogpost.user.User
import com.jh.blogpost.user.UserRepository

import org.springframework.http.ResponseEntity

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.util.*
//import javax.validation.Valid


//@RestController
class PostController {
    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var postRepository: PostRepository
//    @Autowired
//    lateinit var postService: PostService

    @GetMapping("/posts")
    fun getAllPosts(): List<Post>  = postRepository.findAll()

    @GetMapping("/posts/{postId}")
    fun getPostById(@PathVariable postId: Long): Post = postRepository.findById(postId).orElseThrow {  Exception("Post record not found with id: {id}") }

    @GetMapping("/posts/users/{userId}/findByAuthor")
    fun getPostsByAuthor(@PathVariable userId: Long): List<Post> {
        val foundUser: Optional<User> = userRepository.findById(userId)
        val user = foundUser.get()
        return postRepository.findByAuthor(user)
    }

    @GetMapping("/posts/users/{userId}/findByAuthorAndCreatedAtBefore/{hours}")
    fun getAllPostsByAuthorAndCreatedAt(@PathVariable userId: Long, @PathVariable hours: Long): List<Post> {
        val foundUser: Optional<User> = userRepository.findById(userId)
        val user = foundUser.get()
        val datetimeInterval: LocalDateTime = LocalDateTime.now().minusHours(hours)
        return postRepository.findByAuthorAndCreatedAtBefore(user,datetimeInterval)
    }

    @PostMapping("/posts")
    fun createPost(@RequestBody post: Post): Post {
        return try {
//            val foundUser: Optional<User> = userRepository.findById(post.author.id)
//            val user = foundUser.get()
//            val post = BlogPost(post.title, post.content, user)
            postRepository.save(post)
        } catch (ex: Exception) {
            throw Exception(ex)
        }
    }

    @PutMapping("/posts/{postId}")
    fun updatePost(@PathVariable postId: Long, @RequestBody post: Post): Post
    {
        postRepository.findById(postId).orElseThrow { Exception("Post record not found with id: {id}")}
        post.id = postId
        return postRepository.save(post)
    }


    @DeleteMapping("/posts/{postId}")
    fun deletePost(@PathVariable(value = "postId") postId: Long): ResponseEntity<*>? {
        postRepository.findById(postId).orElseThrow { Exception("Post record not found with id: {id}")}
        return ResponseEntity(postRepository.deleteById(postId), HttpStatus.OK)
    }

}





//    @GetMapping("/users/findbyauthor/{userId}/posts")
//    fun getPostsByAuthor(@PathVariable userId: Long,
//                         @RequestParam("page", required = false, defaultValue = "0") page: Int,
//                         @RequestParam("size", required = false, defaultValue = "3") size: Int)
//    : ResponseEntity<Map<String, Any>>
//    {
//        return try {
//            var posts: List<Post>
//
//            val paging: Pageable = PageRequest.of(page, size)
//            val pagePosts: Page<Post> = postRepository.findByAuthor(userId, paging)
//            posts = pagePosts.content
//
//            val response: MutableMap<String, Any> = HashMap()
//            response["posts"] = posts
//            response["currentPage"] = pagePosts.number
//            response["totalItems"] = pagePosts.totalElements
//            response["totalPages"] = pagePosts.totalPages
//
//            ResponseEntity<Map<String, Any>>(response, HttpStatus.OK)
//        } catch (ex: Exception) {
//        return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
//    }
// }