package jvsk.kotlin

sealed interface Maybe<out T> {

    data class Just<T>(val value: T) : Maybe<T>

    data object None : Maybe<Nothing>
}

fun CharSequence.findChar(c: Char): Maybe<Int> {
    for (i in indices) {
        if (get(i) == c) {
            return Maybe.Just(i)
        }
    }
    return Maybe.None
}

fun firstSegment(string: CharSequence): CharSequence =
    when (val index = string.findChar(':')) {
        is Maybe.Just -> string.subSequence(0, index.value)
        Maybe.None -> string
    }
