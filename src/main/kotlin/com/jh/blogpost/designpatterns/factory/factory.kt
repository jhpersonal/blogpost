package com.jh.blogpost.designpatterns.factory

// Factory Method: “Define an interface for creating an object but let subclasses decide which class to instantiate”

// STEP 1: Create the virus interface
interface Virus {
    fun mutate()
}



// STEP 2: START: Create concrete virus classes that implement the Virus interface
class CoronaVirus: Virus {
    override fun mutate() {
        println("Mutating the corona virus...")
    }
}

class InfluenzaVirus: Virus {
    override fun mutate() {
        println("Mutating the flu virus...")
    }
}

class HIVVirus: Virus {
    override fun mutate() {
        println("Mutating the HIV virus...")
    }
}
// END: Create concrete virus classes that implement the Virus interface




// STEP 3: Create an enumeration which will hold virus types (constants)
enum class VirusType {
    CORONA_VIRUS,
    INFLUENZA,
    HIV
}



// STEP 4: Create the VirusFactory which will eventually generate a proper Virus given the virus type from the enumeration
// Factories handle the details of object creation. And every time a client needs an object it asks the factory to create that object.
// It encapsulates the knowledge of creating the proper Virus class for each specific VirusType and move this knowledge out of the usage/creation/invoking.
class VirusFactory {
    fun create(type: VirusType): Virus? {
        return when(type) {
            VirusType.CORONA_VIRUS -> CoronaVirus()
            VirusType.INFLUENZA -> InfluenzaVirus()
            VirusType.HIV -> HIVVirus()
            else -> null
        }
    }
}



// STEP 5: VirusFactory usage
fun main() {
    val virusFactory = VirusFactory()
    val virus = virusFactory.create(VirusType.CORONA_VIRUS)
    virus?.mutate()
}