package com.jh.blogpost.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.stereotype.Repository

//@Repository
@RepositoryRestResource(collectionResourceRel = "users", path="users")
interface UserRepository : JpaRepository <User, Long> {
    fun findByEmail(email: String): User?
    fun findByName(name: String): List<User>

}