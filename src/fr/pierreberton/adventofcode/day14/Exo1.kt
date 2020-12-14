package fr.pierreberton.adventofcode.day14

import java.io.File
import java.math.BigInteger
import java.nio.file.Files

class Exo1 {
    companion object {

        @JvmStatic
        fun main(vararg args: String) {

            var mask = ""
            val memoryMap = mutableMapOf<Int, BigInteger>()

            Files.readAllLines(File(Exo1::class.java.getResource("./day14.input").path).toPath())
                .forEach {
                    val parts = it.split(" = ")
                    if (parts[0].startsWith("mask")) {
                        mask = parts[1]
                    } else {
                        val memAddr = parts[0].drop(4).takeWhile { c -> c != ']' }.toInt()
                        memoryMap[memAddr] = applyMask(mask, parts[1].toBigInteger())
                    }
                }

            println(memoryMap.values.reduce { a, b -> a + b })
        }

        private fun applyMask(mask: String, newValue: BigInteger): BigInteger {
            var valBin = newValue.toByteArray().toBooleanArray()
            var completeMask = mask

            if (completeMask.length < 64) {
                for (i in 0 until (64 - completeMask.length)) {
                    completeMask = "X$completeMask"
                }
            }

            if (valBin.size < 64) {
                val offset = BooleanArray(64 - valBin.size)
                valBin = offset + valBin
            }

            for (i in completeMask.indices) {
                when (completeMask[i]) {
                    '0', '1' -> valBin[i] = (completeMask[i] == '1')
                }
            }

            return BigInteger(valBin.toByteArray())
        }

    }
}