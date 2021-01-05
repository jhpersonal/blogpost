package com.jh.blogpost.post

import com.jh.blogpost.user.User
import org.springframework.data.domain.Page
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.stereotype.Repository
import java.sql.Time
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime
import java.util.*

//@RepositoryRestResource(collectionResourceRel = "posts", path="posts")
//@Repository
interface PostRepository : JpaRepository <Post, Long> {
    fun findByAuthor(author: User): List<Post>
    fun findByAuthorAndCreatedAtBefore(author: User, createdAt: LocalDateTime) : List<Post>

//    fun findByAuthor(userId: Long, pageable: Pageable): Page<Post>    //findByUserId
//    @Query("FROM User WHERE first_name = :firstName")
//    @Query("SELECT first_name, random() AS luckyNumber FROM person", nativeQuery = true)
//    fun findByAuthorAndInterval(userId: Long, time: Time): List<Post>

}
