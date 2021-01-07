package com.jh.blogpost.postpublisher

import com.jh.blogpost.publication.Publication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PostPublisherService(@Autowired val publishers: List<PostPublisher>) {
    fun publish(publication: Publication) {
        publishers.first { it.supports(publication) }.publish(publication)
    }
}

