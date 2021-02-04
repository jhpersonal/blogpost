package com.jh.blogpost.designpatterns.singleton



object Singleton {
    init {
        println("Singleton initialized")
    }

    var message = "Kotlin rock"

    fun showMessage() {
        println(message)
    }
}

class InvokeSingleton {
    init {
        Singleton.showMessage()
    }
}

fun main() {
    Singleton.showMessage()
    Singleton.message = "Kotlin is cool"

    InvokeSingleton()
}