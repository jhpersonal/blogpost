package com.jh.blogpost.designpatterns.singleton

// Singleton Class in Kotlin is also called as the Singleton Object in Kotlin.
// Singleton class is a class that is defined in such a way that only one instance of the class can be created and used everywhere.
// When we use an object instead of a class, Kotlin actually uses the Singelton and allocates the single memory.
// The object class can have functions, properties, and the init method.
// The constructor method is not allowed in an object so we can use the init method if some initialization is required and the object can be defined inside a class.
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