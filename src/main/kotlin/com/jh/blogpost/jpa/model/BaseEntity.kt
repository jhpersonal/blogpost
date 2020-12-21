package com.jh.blogpost.jpa.model

import javax.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@MappedSuperclass
//    @JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
abstract class BaseEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,
    //    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    val createdAt: LocalDateTime = LocalDateTime.now(),

    //    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    val updatedAt: LocalDateTime = LocalDateTime.now()
)

