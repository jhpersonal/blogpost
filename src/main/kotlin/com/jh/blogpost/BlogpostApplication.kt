package com.jh.blogpost

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BlogpostApplication

fun main(args: Array<String>) {
    runApplication<BlogpostApplication>(*args)
}
