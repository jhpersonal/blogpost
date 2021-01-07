package com.jh.blogpost.integration

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.jh.blogpost.facebook.FacebookCredentials
import com.jh.blogpost.facebook.LocalCredentials
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(
    JsonSubTypes.Type(name = "facebook", value= FacebookCredentials::class),
    JsonSubTypes.Type(name = "twitter", value= LocalCredentials::class),
    JsonSubTypes.Type(name = "local", value= LocalCredentials::class)
)
open class IntegrationCredentials(
    @Column(unique = true, length=100, nullable=false)
    open var name: String = "",
    @Column(length=20, nullable=false)
    open var password: String = ""
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id: Long = 0
}