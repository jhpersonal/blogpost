package com.jh.blogpost.user

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.jh.blogpost.role.Role
import org.hibernate.validator.constraints.Length
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
@Table(name="`users`")
@JsonIgnoreProperties("unknown")
data class User(
        @Column(nullable = false)
        val name: String = "",

        @Column(unique = true)
//        @Email
        var email: String = "",

//        @Length(min = 5, message = "*Password must have at least 5 characters")
//        @NotEmpty(message = "*Please provide your password")
//        @NotNull(message = "*Password is required")
        @Column(nullable = false, length = 255)
        val password: String = "",

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "users_roles",
                joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")] )
        var roles: List<Role> = mutableListOf<Role>()
) {
        var createdAt: LocalDateTime = LocalDateTime.now()
        val updatedAt: LocalDateTime = LocalDateTime.now()
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0
}
//        : BaseEntity()
