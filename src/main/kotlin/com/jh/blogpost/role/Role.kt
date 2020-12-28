package com.jh.blogpost.role

import com.jh.blogpost.user.User
import javax.persistence.*

@Entity
@Table(name = "`roles`")
class Role(
    val name: String = "",
    @ManyToMany(mappedBy = "roles")
    val users: Collection<User>
)
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     var id: Long = 0
}