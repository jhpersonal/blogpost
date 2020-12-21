package com.jh.blogpost.publication

import com.jh.blogpost.integration.IntegrationCredentialsRepository
import com.jh.blogpost.post.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.stereotype.Service

@Service
class PublicationService {
    @Autowired
    lateinit var publicationRepository: PublicationRepository
    @Autowired
    lateinit var postRepository: PostRepository
    @Autowired
    lateinit var integrationCredentialsRepository: IntegrationCredentialsRepository


    fun publicationRequest(postId: Long, publicationType: String, integrationCredentialsId: Long): Unit {
        // Verify if it's a valid Post
        postRepository.findByIdOrNull(postId)
            ?: throw ResourceNotFoundException("Post id ${postId} not found")

        // Verify if the publication type is valid
        if (!PublicationType.values().any {it.name == publicationType}) {
            throw  ResourceNotFoundException("publication type $publicationType not found")
        }

        // Verify if the integration credentials is valid
        integrationCredentialsRepository.findByIdOrNull(integrationCredentialsId)
            ?: throw ResourceNotFoundException("Credentials id $integrationCredentialsId not found")

        val post = postRepository.getOne(postId)
        val pubTypeEnum = PublicationType.valueOf(publicationType)
        (pubTypeEnum).printTitle(post.title)
    }


}