package com.jh.blogpost.post

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.jh.blogpost.user.User
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.rest.core.annotation.RestResource
import java.time.LocalDateTime
import javax.persistence.*

@RestResource(path="posts")
@Entity(name = "post")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(
    JsonSubTypes.Type(name = "blog", value= BlogPost::class),
    JsonSubTypes.Type(name = "how-to", value= HowToPost::class),
    JsonSubTypes.Type(name = "blank", value= BlankPost::class)
)
//@JsonIgnoreProperties(ignoreUnknown = true)     //TODO: any alternate approaches??
// @DiscriminatorFormula("case when author is not null then 1 else 2 end")
open class Post(
    @Column(unique = true, length=100, nullable=false)
    open val title: String = "",
//    @Lob
    open var content: String?,
//    @JsonBackReference
    @ManyToOne(fetch=FetchType.LAZY)   // @ManyToOne annotation to declare that it has a many-to-one relationship with the User entity.
    @JoinColumn(name = "user_id", nullable = false)     // @joincolumn annotation to declare the foreign key column.
    open val author: User
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id: Long = 0    // TODO: var id: String = UUID.randomUUID().toString()
    @CreationTimestamp
    open val createdAt: LocalDateTime = LocalDateTime.now()
    @UpdateTimestamp
    open val updatedAt: LocalDateTime = LocalDateTime.now()

}















//@ManyToOne(fetch = FetchType.LAZY, optional = false)    // @ManyToOne annotation to declare that it has a many-to-one relationship with the User entity.
//@JoinColumn(name = "user_id", nullable = false)     // @JoinColumn annotation to declare the foreign key column.
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//val author: User,