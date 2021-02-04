package com.jh.blogpost.designpatterns.singleton

// Kotlin’s representation of a Singleton class requires the object keyword only.
// An object class can contain properties, functions and the init method.
// The constructor method is NOT allowed.
// An object cannot be instantiated in the way a class is instantiated.
// An object gets instantiated when it is used for the first time providing lazy initialization.
// Object declaration’s initialization is thread-safe.

// Singleton Class in Kotlin is also called as the Singleton Object in Kotlin.
// We call the method and member variables present in the singleton class using the class name.
// Examples: to maintain a single instance of resources like a database or object store or file system.
object DatabaseInstance {
    init {
        println("Singleton initialized the default database 'defaultdb'")
    }

    var database = "defaultdb"

    fun setConnection() {
        println(database)
    }
}

// Invoke Singleton object
class Singleton {
    init {
        DatabaseInstance.setConnection()
    }
}

fun main() {
    DatabaseInstance.setConnection()
    DatabaseInstance.database = "blogpostdb"

    Singleton()
}