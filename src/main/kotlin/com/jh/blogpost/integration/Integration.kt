package com.jh.blogpost.integratio

import com.jh.blogpost.integration.IntegrationCredentials
import javax.persistence.*

@Entity
@Table(name="integration")
data class Integration(
    var name: String = "",
    @Enumerated(EnumType.STRING)
    val type: Integration_Type,
    @ManyToOne   // @ManyToOne annotation to declare that it has a many-to-one relationship with the IntegrationCredentials entity.
    @JoinColumn(name = "integration_id", nullable = false)     // @JoinColumn annotation to declare the foreign key column.
    var integrationCredentials: IntegrationCredentials,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
)

enum class Integration_Type {
    Facebook, Twitter, Local, linkedin
}
