package com.jh.blogpost.role

import com.jh.blogpost.user.User
import org.springframework.security.core.GrantedAuthority
import javax.persistence.*

@Entity
@Table(name = "role")
class Role(
    @Column(unique = true, nullable = false)
    val name: String = ""
): GrantedAuthority
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     var id: Long = 0
    override fun getAuthority() = name
}


