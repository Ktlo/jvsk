package jvsk.kotlin

inline fun <reified T> assureIs(subject: Any?): T {
    if (subject !is T) {
        error("wrong type")
    }
    return subject
}

fun exa(value: Any) {

    assureIs<Int>(value)
}
