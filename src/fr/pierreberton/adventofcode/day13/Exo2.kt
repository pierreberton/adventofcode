package fr.pierreberton.adventofcode.day13

import java.io.File
import java.math.BigInteger
import java.nio.file.Files

class Exo2 {
    companion object {

        @JvmStatic
        fun main(vararg args: String) {

            val lines = Files.readAllLines(
                File(Exo2::class.java.getResource("./day13.input").path).toPath()
            )

            val busLines = lines.last().split(",").map { if (it == "x") null else it.toLong() }

            val busAndOffset = mutableListOf<Pair<BigInteger, BigInteger>>()
            var cpt = 0L
            busLines.forEach {
                if (it != null) {
                    busAndOffset.add(Pair(it.toBigInteger(), cpt.toBigInteger()))
                }
                cpt++
            }

            var n = BigInteger.ONE
            busAndOffset.forEach { n *= it.first }

            var x = BigInteger.ZERO
            busAndOffset.forEach {
                val nr = n / it.first
                val (_, _, v) = gcd(it.first, nr)
                val e = nr * v
                x += e * (BigInteger.ZERO - it.second)
            }

            println(x % n)

        }

        private fun gcd(p: BigInteger, q: BigInteger): Triple<BigInteger, BigInteger, BigInteger> {
            if (q == BigInteger.ZERO) return Triple(p, BigInteger.ONE, BigInteger.ZERO)
            val vals = gcd(q, p % q)
            val d = vals.first
            val a = vals.third
            val b = vals.second - p / q * vals.third
            return Triple(d, a, b)
        }
    }
}