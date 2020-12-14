package fr.pierreberton.adventofcode.day14

import java.io.File
import java.math.BigInteger
import java.nio.file.Files

class Exo2 {
    companion object {

        @JvmStatic
        fun main(vararg args: String) {

            var mask = ""
            val memoryMap = mutableMapOf<BigInteger, BigInteger>()

            Files.readAllLines(File(Exo2::class.java.getResource("./day14.input").path).toPath())
                .forEach {
                    val parts = it.split(" = ")
                    if (parts[0].startsWith("mask")) {
                        mask = parts[1]
                    } else {
                        val memAddr = parts[0].drop(4).takeWhile { c -> c != ']' }.toInt()
                        applyMask(mask, parts[1].toBigInteger(), memAddr.toBigInteger(), memoryMap)
                    }
                }

            println(memoryMap.values.reduce { a, b -> a + b })
        }

        private fun applyMask(
            mask: String,
            value: BigInteger,
            address: BigInteger,
            memoryMap: MutableMap<BigInteger, BigInteger>
        ) {
            var addrBin = address.toByteArray().toBooleanArray()
            var completeMask = mask

            if (completeMask.length < 64) {
                for (i in 0 until (64 - completeMask.length)) {
                    completeMask = "0$completeMask"
                }
            }

            if (addrBin.size < 64) {
                val offset = BooleanArray(64 - addrBin.size)
                addrBin = offset + addrBin
            }

            var newAddr = ""
            for (i in completeMask.indices) {
                when (completeMask[i]) {
                    '0' -> newAddr += if (addrBin[i]) '1' else '0'
                    '1', 'X' -> newAddr += completeMask[i]
                }
            }

            val possibilities = getAllPossibilities(newAddr)

            possibilities.forEach { memoryMap[it] = value }
        }

        private fun getAllPossibilities(newAddr: String): MutableList<BigInteger> {
            val result = mutableListOf<BigInteger>()

            val nbPoss = newAddr.filter { it == 'X' }.length

            for (i in 0 until (2 pow nbPoss)) {
                val bArray = byteArrayOf(0) + BigInteger.valueOf(i.toLong()).toByteArray()
                val valToSet = bArray.toBooleanArray().takeLast(nbPoss)
                var currentAddr = newAddr
                valToSet.forEach {
                    currentAddr = currentAddr.replaceFirst('X', if (it) '1' else '0')
                }
                result.add(BigInteger(currentAddr.toByteArray()))
            }

            return result
        }

    }
}