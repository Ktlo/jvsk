package jvsk.kotlin

fun interface Magma<M> {

    fun operation(a: M, b: M): M
}

context(Magma<M>)
infix operator fun <M> M.times(other: M): M = operation(this, other)

fun interface Semigroup<M> : Magma<M>

fun interface Wrapping<in E, out M> {

    fun wrap(subject: E): M
}

context(Semigroup<M>, Wrapping<E, M>)
fun <E, M> M.prepend(element: E): M = wrap(element) * this

// Использование

fun <E> listSemigroup() = Semigroup<List<E>> { a, b -> a + b }

fun <E> listWrapping() = Wrapping<E, List<E>> { subject -> listOf(subject)  }

fun mainCRT1() {
    with(listSemigroup<Int>()) {
        with(listWrapping<Int>()) {

            val list = listOf(1, 2, 3)

            val newList = list.prepend(0)

            println(newList)
        }
    }
}

// Другой пример использования

sealed interface Chain<out E> {

    data class Cons<E>(val element: E, val tail: Chain<E>) : Chain<E>

    data object Nil : Chain<Nothing>
}

infix fun <E> Chain<E>.concat(other: Chain<E>): Chain<E> =
    when (this) {
        is Chain.Cons -> Chain.Cons(element, tail.concat(other))
        Chain.Nil -> other
    }

fun <E> chainSemigroup() = Semigroup<Chain<E>> { a, b -> a concat b }

fun <E> chainWrapping() = Wrapping<E, Chain<E>> { Chain.Cons(it, Chain.Nil) }

fun mainCRT2() {
    with(chainSemigroup<Int>()) {
        with(chainWrapping<Int>()) {

            val chain = Chain.Cons(2, Chain.Cons(3, Chain.Nil))

            val newChain = chain.prepend(1)

            println(newChain)
        }
    }
}
