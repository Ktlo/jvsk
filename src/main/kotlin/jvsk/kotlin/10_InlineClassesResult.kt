package jvsk.kotlin

@JvmInline
value class Result<out T, out E> @PublishedApi internal constructor(@PublishedApi internal val value: Any?) {

    @PublishedApi internal class Failure<out E>(val error: E)

    companion object {

        fun <T> success(value: T): Result<T, Nothing> = Result(value)

        fun <E> failure(value: E): Result<Nothing, E> = Result(Failure(value))
    }
}

@Suppress("UNCHECKED_CAST")
inline fun <T, E, R> Result<T, E>.handle(onSuccess: (T) -> R, onFailure: (E) -> R): R =
    if (value !is Result.Failure<*>) {
        onSuccess(value as T)
    } else {
        onFailure(value.error as E)
    }

@Suppress("UNCHECKED_CAST")
inline fun <A, B, E> Result<A, E>.flatMap(functor: (A) -> Result<B, E>): Result<B, E> =
    handle({ functor(it) }, { this as Result<B, E> })

inline fun <A, B, E> Result<A, E>.andThen(functor: (A) -> Result<B, E>): Result<B, E> = flatMap(functor)

inline fun <A, B, E> Result<A, E>.map(functor: (A) -> B): Result<B, E> =
    flatMap { Result.success(functor(it)) }

inline fun <T, A, B> Result<T, A>.flatMapError(functor: (A) -> Result<T, B>): Result<T, B> =
    handle({ Result.success(it) }, { functor(it) })

inline fun <T, A, B> Result<T, A>.orThen(functor: (A) -> Result<T, B>): Result<T, B> = flatMapError(functor)

inline fun <T, A, B> Result<T, A>.mapError(functor: (A) -> B): Result<T, B> =
    flatMapError { Result.failure(functor(it)) }

class ResultException(val error: Any?) : RuntimeException() {

    override val message = "Expecting success, but got failure with error: $error"
}

fun <T> Result<T, *>.throwing(): T = handle({ it }, { throw ResultException(it) })
