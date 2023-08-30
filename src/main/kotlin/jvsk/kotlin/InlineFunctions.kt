package jvsk.kotlin

interface Event

inline fun forever(block: () -> Unit): Nothing {
    while (true) {
        block()
    }
}

interface Disposable {

    fun dispose()
}

interface EventPoller : Disposable {

    fun pollEvent(): Event?
}

inline fun <T : Disposable, R> T.disposing(block: (T) -> R): R {
    try {
        return block(this)
    } finally {
        dispose()
    }
}

inline fun <R> withEventPoller(block: (EventPoller) -> R): R {
    return eventPoller().disposing(block)
}

external fun eventPoller(): EventPoller

fun nextEvent(): Event {
    withEventPoller {
        forever {
            val event = it.pollEvent()
            if (event != null) {
                return event
            }
        }
    }
}

fun gotoExample(value: Boolean) {
    run label@{

        if (value) {
            return@label
        }

        println("no goto")
    }
    // label:
}
