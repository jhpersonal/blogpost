package com.jh.blogpost.facebook

import com.jh.blogpost.postpublisher.PostPublisher
import com.jh.blogpost.publication.Publication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TwitterPostPublisher: PostPublisher {
    @Autowired
    lateinit var twitterService: TwitterService

    override fun supports(publication: Publication): Boolean {
        return publication.integration is LocalIntegration
    }

    override fun publish(publication: Publication) {
        twitterService.printTitle(publication.post.title, publication.integration)
    }
}
