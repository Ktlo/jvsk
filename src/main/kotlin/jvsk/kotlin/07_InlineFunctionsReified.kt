package jvsk.kotlin

inline fun <reified T> assureIs(subject: Any?, onFail: () -> String): T {
    if (subject !is T) {
        error(onFail())
    }
    return subject
}

fun interface Later<T> {

    fun get(): T
}

inline fun <reified T> assureIsLater(subject: Any?, noinline onFail: () -> String): Later<T> {
    return Later { assureIs(subject, onFail) }
}
