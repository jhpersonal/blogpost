package com.jh.blogpost.publication

import com.jh.blogpost.Publisher.PostPublisher
import com.jh.blogpost.post.Post

enum class PublicationType: PostPublisher {
//    LOCAL, FACEBOOK, TWITTER
    LOCAL {
    override fun printTitle(title: String) { println("Local: $title")  }
    },
    FACEBOOK {
        override fun printTitle(title: String) { println("Facebook: $title")  }
    },
    Twitter {
        override fun printTitle(title: String) { println("Twitter: $title")  }
    }
}