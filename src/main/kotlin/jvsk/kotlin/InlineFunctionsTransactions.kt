package jvsk.kotlin

interface Transaction {

    fun commit()

    fun rollback()
}

external fun createTransaction(): Transaction

inline fun <R> transaction1(block: () -> R): R {
    val transaction = createTransaction()
    try {
        val result = block()
        transaction.commit()
        return result
    } catch (throwable: Throwable) {
        transaction.rollback()
        throw throwable
    }
}

inline fun <R> transaction2(crossinline block: () -> R): R {
    val transaction = createTransaction()
    try {
        val result = block()
        transaction.commit()
        return result
    } catch (throwable: Throwable) {
        transaction.rollback()
        throw throwable
    }
}

inline fun <R> transaction3(block: () -> R): R {
    val transaction = createTransaction()
    var isError = false
    try {
        return block()
    } catch (throwable: Throwable) {
        transaction.rollback()
        isError = true
        throw throwable
    } finally {
        if (!isError) {
            transaction.commit()
        }
    }
}
