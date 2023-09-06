package jvsk.kotlin

interface TransactionInfo {

    fun execute(query: String, params: List<Any>): Int
}

interface TransactionContext {

    val transaction: TransactionInfo
}

external fun getTransaction(): TransactionInfo

context(TransactionContext)
fun insertObject1(subject: Any) {
    transaction.execute("INSERT INTO OBJECTS (VALUE) VALUES (?)", listOf(subject))
}

fun useInsertObject1(subject: Any) {
    val transaction = getTransaction()
    val transactionContext = object : TransactionContext { override val transaction = transaction }

    with(transactionContext) {
        insertObject1(subject)
    }
}

fun insertObject2(this1: TransactionContext, subject: Any) {
    this1.transaction.execute("INSERT INTO OBJECTS (VALUE) VALUES (?)", listOf(subject))
}

interface Logger {

    fun write(line: String)
}

interface LoggerContext {

    val logger: Logger
}

context(LoggerContext, TransactionContext)
fun insertObject3(subject: Any) {
    insertObject1(subject)
    logger.write("inserted object $subject")
}

context(LoggerContext)
class MyComponent(private val regularArgument: String) {

    init {
        logger.write("MyComponent initialized")
    }
}
