package com.jh.blogpost.integration

import com.jh.blogpost.integration.Integration
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource
interface IntegrationRepository: JpaRepository<Integration, Long> {
}