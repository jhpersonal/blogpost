package com.jh.blogpost.publication

import com.jh.blogpost.integratio.Integration
import com.jh.blogpost.integratio.Integration_Type
import com.jh.blogpost.integration.IntegrationCredentials
import com.jh.blogpost.post.Post
import com.jh.blogpost.user.User
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.*

@Entity
@Table(name="`publications`")
data class Publication(
    @ManyToOne   // @ManyToOne annotation to declare that it has a many-to-one relationship with the Post entity.
    @JoinColumn(name = "post_id", nullable = false)     // @JoinColumn annotation to declare the foreign key column.
    var publicationPost: Post,
    @ManyToOne   // @ManyToOne annotation to declare that it has a many-to-one relationship with the IntegrationCredentials entity.
    @JoinColumn(name = "integration_credentials_id", nullable = false)     // @JoinColumn annotation to declare the foreign key column.
    var integrationCredentials: IntegrationCredentials,
    @Enumerated(EnumType.STRING)
    val type: PublicationType = PublicationType.LOCAL

) {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
    @CreationTimestamp
    val createdAt: LocalDateTime? = LocalDateTime.now()
    @UpdateTimestamp
    val updatedAt: LocalDateTime? = LocalDateTime.now()
}