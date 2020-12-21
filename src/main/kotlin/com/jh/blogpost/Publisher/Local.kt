package com.jh.blogpost.Publisher

import com.jh.blogpost.post.Post

class Local: PostPublisher {
//    override fun printTitle(postTitle: String) {
//        println("Twitter: $postTitle")
//    }
override fun printTitle(postTitle: String) {
    println("Local: $postTitle")
}
}