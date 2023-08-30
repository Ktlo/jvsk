@file:OptIn(ExperimentalStdlibApi::class)

package jvsk.kotlin

@JvmInline
value class Color private constructor(private val value: Int) {

    constructor(red: Byte, green: Byte, blue: Byte, alpha: Byte = -1) : this(
        int(red) shl 8 or int(green) shl 8 or int(blue) shl 8 or int(alpha)
    )

    val red get() = (value ushr 24).toByte()

    val green get() = (value ushr 16 and 0xFF).toByte()

    val blue get() = (value ushr 8 and 0xFF).toByte()

    val alpha get() = (value and 0xFF).toByte()

    operator fun component1() = red

    operator fun component2() = green

    operator fun component3() = blue

    operator fun component4() = alpha

    override fun toString() = "Color(" + value.toUInt().toHexString(HexFormat.UpperCase) + ")"
}

private fun int(byte: Byte) = byte.toInt() and 0xFF

fun tets(color: Color) {
    val (r, g, b, a) = color
}
