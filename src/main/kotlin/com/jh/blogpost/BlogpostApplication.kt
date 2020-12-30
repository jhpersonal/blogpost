package com.jh.blogpost

import com.jh.blogpost.user.UserRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
class BlogpostApplication

fun main(args: Array<String>) {
    runApplication<BlogpostApplication>(*args)
//    SpringApplication.run(InMemoryAuthApplication::class.java, args)
}
