package com.jh.blogpost.post

import com.jh.blogpost.user.User

import java.time.LocalDateTime
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("blog")
class BlogPost(
    title: String,
    content: String?,
    author: User
): Post(title, content, author)