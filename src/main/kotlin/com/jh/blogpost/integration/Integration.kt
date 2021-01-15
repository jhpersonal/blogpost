package com.jh.blogpost.integration

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.jh.blogpost.facebook.FacebookIntegration
import com.jh.blogpost.facebook.LocalIntegration
import com.jh.blogpost.facebook.TwitterIntegration
import com.jh.blogpost.integration.IntegrationCredentials
import org.springframework.data.rest.core.annotation.RestResource
import javax.persistence.*

@RestResource(path="integrations")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(
    JsonSubTypes.Type(name = "facebook", value= FacebookIntegration::class),
    JsonSubTypes.Type(name = "twitter", value= TwitterIntegration::class),
    JsonSubTypes.Type(name = "local", value= LocalIntegration::class)
)
open class Integration(
    open var name: String = "",
    @OneToOne   // @OneToOne annotation to declare that it has a one-to-one relationship with the IntegrationCredentials entity.
    open var integrationCredentials: IntegrationCredentials,

) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id: Long = 0
}
