package com.jh.blogpost.facebook

import com.jh.blogpost.postpublisher.PostPublisher
import com.jh.blogpost.publication.Publication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class FacebookPostPublisher: PostPublisher {
    @Autowired
    lateinit var facebookService: FacebookService

    override fun supports(publication: Publication): Boolean {
        return publication.integration is FacebookIntegration
    }

    override fun publish(publication: Publication) {
        facebookService.printTitle(publication.post.title, publication.integration)
    }
}
