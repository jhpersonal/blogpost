package com.jh.blogpost.postpublisher

import com.jh.blogpost.publication.Publication

interface PostPublisher {
 fun supports(publication: Publication): Boolean
 fun publish(publication: Publication)
}