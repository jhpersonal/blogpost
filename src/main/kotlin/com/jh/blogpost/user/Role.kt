package com.jh.blogpost.user

import javax.persistence.*

@Entity
@Table(name = "'roles'")
class Role(
    val name: String = "",
    @ManyToMany(mappedBy = "roles")
    val users: Collection<User>
)
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     val id: Long = 0
}