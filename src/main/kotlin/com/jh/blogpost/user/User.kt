package com.jh.blogpost.user

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.jh.blogpost.role.Role
import org.hibernate.validator.constraints.Length
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
@Table(name="`user`")
@JsonIgnoreProperties("unknown")
data class User(
        @Column(nullable = false)
        val name: String = "",

        @Column(unique = true)
        @Email
        var email: String = "",

//        @Length(min = 5, message = "*Password must have at least 5 characters")
        @NotBlank(message = "Password is mandatory")
        @Column(nullable = false, length = 255)
        private val password: String = "",

        @ManyToMany(fetch = FetchType.EAGER)
        var roles: List<Role> = mutableListOf<Role>()
): UserDetails {
        var createdAt: LocalDateTime = LocalDateTime.now()
        val updatedAt: LocalDateTime = LocalDateTime.now()
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0
        override fun getAuthorities() = roles
        override fun getPassword() = password
        override fun getUsername() = email
        override fun isAccountNonExpired() = true
        override fun isAccountNonLocked() = true
        override fun isCredentialsNonExpired() = true
        override fun isEnabled() = true
}
//        : BaseEntity()
