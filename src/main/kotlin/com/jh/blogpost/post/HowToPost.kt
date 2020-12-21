package com.jh.blogpost.post

import com.jh.blogpost.user.User

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("how-to")
open class HowToPost (
    title: String,
    content: String?,
    author: User
) : Post(title, content, author)