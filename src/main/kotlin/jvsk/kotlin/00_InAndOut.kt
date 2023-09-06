package jvsk.kotlin

fun interface Inbound<in T> {

    fun absorb(value: T)
}

fun <T : Any> useInbound(inbound: Inbound<T>) {
    // <T extends Object> void useInbound(Inbound<? super T> inbound) {}
}

fun interface Outbound<out T> {

    fun release(): T
}

fun <T : Any> useOutbound(outbound: Outbound<T>) {
    // <T extends Any> void useOutbound(Outbound<? extends T>);
}

// В некоторых случаях:
fun <T> useComparator(comparator: Comparator<in T>, a: T, b: T): Int {
    return comparator.compare(a, b)
}

fun interface AsyncOutbound<out T> {

    fun asyncRelease(consumer: (T) -> Unit)
}

fun interface LazyInbound<in T> {

    fun lazyAbsorb(producer: () -> T)
}

///

fun <T> createList(a: T, b: T): List<T> {
    // <T> List<? extends T> createList(T a, T b);
    return listOf(a, b)
}

///

interface Bound<T> : Inbound<T>, Outbound<T>

fun <T> useAsInbound(bound: Bound<in T>) {
    val item: Any? = bound.release()
}

fun <T> useAsOutbound(bound: Bound<out T>, value: Nothing) {
    bound.absorb(value)
}
