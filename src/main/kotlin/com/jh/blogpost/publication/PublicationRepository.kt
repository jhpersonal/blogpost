package com.jh.blogpost.publication

import com.jh.blogpost.post.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.stereotype.Repository

//@Repository
@RepositoryRestResource(collectionResourceRel = "publication", path="publication")
interface PublicationRepository: JpaRepository<Publication, Long> {
//    fun findByPublicationPost(publicationPost: Post): List<Publication>

}