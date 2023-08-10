package jvsk.kotlin

sealed interface Maybe<out T> {

    data class Just<out T>(val value: T) : Maybe<T>

    data object None : Maybe<Nothing>
}
