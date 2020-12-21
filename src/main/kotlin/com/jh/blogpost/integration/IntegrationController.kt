package com.jh.blogpost.integration

import com.jh.blogpost.publication.Publication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

//@RestController
class IntegrationController {
    @Autowired
    lateinit var integrationCredentialsRepository: IntegrationCredentialsRepository

    @GetMapping("/integrationCredentials")
    fun getAllIntegrationCredentials(): List<IntegrationCredentials>  = integrationCredentialsRepository.findAll()

    @GetMapping("/integrationCredentials/{integrationCredentialsId}")
    fun getPublicationsById(@PathVariable integrationCredentialsId: Long): IntegrationCredentials =
        integrationCredentialsRepository.findById(integrationCredentialsId).orElseThrow {  Exception("Integration Credentials record not found with id: {id}") }

    @PostMapping("/integrationCredentials")
    fun createPublications(@RequestBody publication: IntegrationCredentials): IntegrationCredentials {
        return try {
            integrationCredentialsRepository.save(publication)
        } catch (ex: Exception) {
            throw Exception(ex)
        }
    }
}