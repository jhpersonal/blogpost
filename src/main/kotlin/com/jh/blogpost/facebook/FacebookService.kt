package com.jh.blogpost.facebook


import com.jh.blogpost.integration.Integration
import org.springframework.stereotype.Service

@Service
class FacebookService {
    fun printTitle(postTitle: String, integration: Integration){
        println("Facebook Post: $postTitle")
    }
}
