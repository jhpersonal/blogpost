package com.jh.blogpost.user

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.jh.blogpost.jpa.model.BaseEntity
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.*

@Entity
@Table(name="`users`")
@JsonIgnoreProperties("unknown")
data class User(
        var name: String = "",
        @Column(unique = true)
        var email: String = "",
        @Column(nullable=false, length = 255)
        var password: String = "",
        ) {
        var createdAt: LocalDateTime = LocalDateTime.now()
        val updatedAt: LocalDateTime = LocalDateTime.now()
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0
}
//        : BaseEntity()
