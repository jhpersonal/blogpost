package com.jh.blogpost.integration

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource
interface IntegrationCredentialsRepository: JpaRepository<IntegrationCredentials, Long> {

}

