package jvsk.kotlin

import java.util.UUID

@JvmInline
value class Password(val password: String) {

    override fun toString(): String = "<hidden>"
}

data class WithSensitiveData(
    val id: UUID,
    val password: Password,
)
