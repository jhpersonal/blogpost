package com.jh.blogpost.post

import com.jh.blogpost.user.User
import com.jh.blogpost.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import java.time.Month




@Service
class PostService() {

    @Autowired
    lateinit var postRepository: PostRepository

    @Autowired
    lateinit var userRepository: UserRepository

    //    CRUD operations
    fun getPosts(): List<Post> = postRepository.findAll()
//
//    fun getPostById(postId: Long): Post {
//        var postDb: Optional<Post> = postRepository.findById(postId)
//        return try {
//            if (postDb.isPresent)
//                postRepository.getOne(postId)
//            else
//                throw Exception("Record not found with id: {postId}")
//        } catch (ex: Exception) {
//            throw Exception(ex.message)
//        }
//    }
//
    fun createPost(post: Post): Post
    {
        return try {
             postRepository.save(post)
         } catch (ex: Exception) {
             throw ResourceNotFoundException("Post id ${post.id} not found")
         }
    }

    fun updatePost(post: Post, postId: Long): Post {
        var postDb: Optional<Post> = postRepository.findById(postId)
        if (!postDb.isPresent) ResourceNotFoundException("Post id $postId not found")

        return try {
                val postUpdate: Post = postDb.get()
                postUpdate.content = post.content
                postRepository.save(postUpdate)

        } catch (ex: Exception) {
            throw Exception(ex.message)
        }
    }

    fun deletePost(postId: Long) {
        var postDb: Optional<Post> = postRepository.findById(postId)
        if (!postDb.isPresent) ResourceNotFoundException("Post id $postId not found")
        return try {
            postRepository.deleteById(postId)
        } catch (ex: Exception) {
            throw Exception(ex.message)
        }
    }

    fun findPostsByAuthor(userId: Long): List<Post> {
        val userDb: Optional<User> = userRepository.findById(userId)
        if (!userDb.isPresent) ResourceNotFoundException("User id $userId not found")
        val author = userDb.get()
        return try {
            postRepository.findByAuthor(author)
        } catch (ex: Exception) {
            throw Exception(ex.message)
        }
    }

    fun findPostByAuthorAndCreatedDate(userId:Long, hours : Long): List<Post> {
        val userDb: Optional<User> = userRepository.findById(userId)
        if (!userDb.isPresent) ResourceNotFoundException("User id $userId not found")
        val author = userDb.get()
        return try {
            postRepository.findByAuthorAndCreatedAtBefore(author,author.createdAt.minusHours(hours))
        } catch (ex: Exception) {
            throw Exception(ex.message)
        }
    }

}