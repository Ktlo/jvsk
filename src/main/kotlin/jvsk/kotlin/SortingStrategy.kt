package jvsk.kotlin

fun interface SortingStrategy<T> {

    fun sort(collection: Collection<T>, comparator: Comparator<in T>): List<T>

    companion object {

        fun <T> createDefault() = SortingStrategy<T> { collection, comparator -> collection.sortedWith(comparator) }
    }
}

fun <T : Comparable<T>> SortingStrategy<T>.sort(collection: Collection<T>): List<T> {
    return sort(collection, Comparator.naturalOrder())
}

fun <T> sortDefault(collection: Collection<T>): List<T>
where T : Number,
      T : Comparable<T>
{
    val sortingStrategy: SortingStrategy<T> = SortingStrategy.createDefault()
    return sortingStrategy.sort(collection)
}

fun k(collection: Collection<Int>) {
    sortDefault(collection)
}

fun e(collection: Collection<Int>) {
    val sortingStrategy: SortingStrategy<Int> = SortingStrategy.createDefault()
    val list: List<Int> = sortingStrategy.sort(collection)
}
