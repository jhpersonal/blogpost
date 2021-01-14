package com.jh.blogpost.facebook

import com.jh.blogpost.integration.Integration
import org.springframework.stereotype.Service

@Service
class TwitterService {
    fun printTitle(postTitle: String, integration: Integration){
        println("Twitter Post: $postTitle")
    }
}
