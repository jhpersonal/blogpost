package com.jh.blogpost.integration

import javax.persistence.*

@Entity
@Table(name="integration_credentials")
data class IntegrationCredentials(
    @Column(unique = true, length=100, nullable=false)
    val username: String = "",
    @Column(length=20, nullable=false)
    val password: String = ""
//    var fbUser: String = "",
//    var fbPassword: String = "",
//    var twUser: String = "",
//    var twPassword: String = ""
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
}