package com.jh.blogpost.publication

import com.jh.blogpost.postpublisher.PostPublisherService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class PublicationController {
    @Autowired
    lateinit var postPublisherService: PostPublisherService
    @Autowired
    lateinit var publicationRepository: PublicationRepository

    @PostMapping("/publish")
    fun publish(@RequestBody requests: List<Long>){
        requests.forEach {
            val publication = publicationRepository.findById(it)
            if(publication.isPresent) {
                postPublisherService.publish(publication.get())
            }
        }
    }
}













/*
import com.jh.blogpost.integration.IntegrationCredentials
import com.jh.blogpost.post.Post
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

//@RestController
class PublicationController {

    @Autowired
    lateinit var publicationRepository: PublicationRepository

    @Autowired
    lateinit var publicationService: PublicationService

    @GetMapping("/publications/type/{publicationType}/posts/{postId}/integrationCredentials/{integrationCredentialsId}")
    fun getPublicationRequests(@PathVariable postId: Long, @PathVariable publicationType: String, @PathVariable integrationCredentialsId: Long): Unit =
        publicationService.publicationRequest(postId, publicationType, integrationCredentialsId )

    @GetMapping("/publications")
    fun getAllPublications(): List<Publication>  = publicationRepository.findAll()

    @GetMapping("/publications/{publicationId}")
    fun getPublicationsById(@PathVariable publicationId: Long): Publication =
        publicationRepository.findById(publicationId).orElseThrow {  Exception("Publication record not found with id: {id}") }

    @PostMapping("/publications")
    fun createPublications(@RequestBody publication: Publication): Publication {
        return try {
            publicationRepository.save(publication)
        } catch (ex: Exception) {
            throw Exception(ex)
        }
    }


}*/