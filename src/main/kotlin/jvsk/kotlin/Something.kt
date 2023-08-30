package jvsk.kotlin

class Something

fun throwError1(): Nothing {
    throw IllegalStateException()
}

fun throwError2(): Nothing {
    error("error")
}

fun throwError3(): Nothing {
    TODO()
}

fun newerReturn(): Nothing {
    while (true) {

    }
}

fun somethingExample() {
    val something0: Any = Something()
    val something1: Something = Something()
    val something2: Something = throwError1()
    val something3: Something = newerReturn()
}

object EmptyCollection : AbstractCollection<Nothing>() {

    override val size get() = 0

    override fun iterator(): Iterator<Nothing> = object : Iterator<Nothing> {
        override fun hasNext() = false
        override fun next(): Nothing = throw NoSuchElementException()
    }
}

fun emptyCollectionExample() {
    val intCollection: Collection<Int> = EmptyCollection
}
