package jvsk.kotlin

private inline fun readLeb128(readByte: () -> Byte): ULong {
    var numRead = 0
    var result = 0uL
    var read: ULong
    do {
        check(numRead < 10) { "LEB128 is too big (bigger than 64 bit)" }
        read = readByte().toULong()
        val value = read and 127uL
        result = result or (value shl 7 * numRead)
        ++numRead
    } while (read and 128uL != 0uL)
    return result
}

interface InputSync {

    fun readByte(): Byte
}

interface InputAsync {

    suspend fun readByte(): Byte
}

fun InputSync.readLeb128() = readLeb128(this::readByte)

suspend fun InputAsync.readLeb128() = readLeb128 { readByte() }
