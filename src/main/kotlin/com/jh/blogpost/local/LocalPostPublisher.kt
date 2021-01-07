package com.jh.blogpost.facebook

import com.jh.blogpost.postpublisher.PostPublisher
import com.jh.blogpost.publication.Publication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LocalPostPublisher: PostPublisher {
    @Autowired
    lateinit var localService: LocalService

    override fun supports(publication: Publication): Boolean {
        return publication.integration is LocalIntegration
    }

    override fun publish(publication: Publication) {
        localService.printTitle(publication.post.title, publication.integration)
    }
}
