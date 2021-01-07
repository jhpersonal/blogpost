package com.jh.blogpost.facebook

import com.jh.blogpost.integration.Integration
import org.springframework.stereotype.Service

@Service
class LocalService {
    fun printTitle(postTitle: String, integration: Integration){
        println("Local: Post $postTitle")
    }
}
