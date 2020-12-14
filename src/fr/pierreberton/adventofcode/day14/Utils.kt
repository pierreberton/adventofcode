package fr.pierreberton.adventofcode.day14

import java.math.BigInteger
import kotlin.math.pow

infix fun Int.pow(exponent: Int): Int = toDouble().pow(exponent).toInt()

fun BooleanArray.toByteArray(): ByteArray {
    return this.joinToString(separator = "") { if (it) "1" else "0" }.chunked(8).map {
        var byte = BigInteger.ZERO
        if (it.isNotEmpty())
            byte = byte or BigInteger(if (it[0] == '1') "80" else "00", 16)

        if (it.length > 1)
            byte = byte or BigInteger(if (it[1] == '1') "40" else "00", 16)

        if (it.length > 2)
            byte = byte or BigInteger(if (it[2] == '1') "20" else "00", 16)

        if (it.length > 3)
            byte = byte or BigInteger(if (it[3] == '1') "10" else "00", 16)

        if (it.length > 4)
            byte = byte or BigInteger(if (it[4] == '1') "08" else "00", 16)

        if (it.length > 5)
            byte = byte or BigInteger(if (it[5] == '1') "04" else "00", 16)

        if (it.length > 6)
            byte = byte or BigInteger(if (it[6] == '1') "02" else "00", 16)

        if (it.length > 7)
            byte = byte or BigInteger(if (it[7] == '1') "01" else "00", 16)

        byte.toByte()
    }.toByteArray()
}

fun ByteArray.toBooleanArray(): BooleanArray {
    val outputArray = BooleanArray(this.size * 8)
    for (i in indices) {
        outputArray[i * 8] = ((get(i).toInt().shr(7) and 1) == 1)
        outputArray[i * 8 + 1] = ((get(i).toInt().shr(6) and 1) == 1)
        outputArray[i * 8 + 2] = ((get(i).toInt().shr(5) and 1) == 1)
        outputArray[i * 8 + 3] = ((get(i).toInt().shr(4) and 1) == 1)
        outputArray[i * 8 + 4] = ((get(i).toInt().shr(3) and 1) == 1)
        outputArray[i * 8 + 5] = ((get(i).toInt().shr(2) and 1) == 1)
        outputArray[i * 8 + 6] = ((get(i).toInt().shr(1) and 1) == 1)
        outputArray[i * 8 + 7] = ((get(i).toInt().shr(0) and 1) == 1)
    }
    return outputArray
}