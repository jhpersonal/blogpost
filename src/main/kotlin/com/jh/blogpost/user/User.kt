package com.jh.blogpost.user

import com.jh.blogpost.jpa.model.BaseEntity
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.*

@Entity
@Table(name="`users`")
data class User(
        var name: String = "",
        @Column(unique = true)
        var email: String = ""
        ) {
        var createdAt: LocalDateTime = LocalDateTime.now()
        val updatedAt: LocalDateTime = LocalDateTime.now()
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0
}
//        : BaseEntity()
