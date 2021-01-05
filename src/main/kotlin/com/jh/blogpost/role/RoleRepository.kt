package com.jh.blogpost.role

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.stereotype.Repository

//@RepositoryRestResource(collectionResourceRel = "roles", path="roles")
interface RoleRepository : JpaRepository <Role, Long> {
    fun findByName(name: String): List<Role>
}