package ru.zentsova.yandex.sprint4

import java.io.BufferedReader

// F. Префиксные хеши

fun main() {
  val reader = System.`in`.bufferedReader()
	val a = reader.readInt()
	val mod = reader.readInt()
	val str = reader.readLine()
	val prefixHashes = prefixHashes(str, a, mod)
	val powers = powers(str, a, mod)
	val result = buildList {
		repeat(reader.readInt()) {
			val (start, end) = reader.readRange()
			add(hash(prefixHashes, powers, start, end, mod))
		}
	}
	print(result.joinToString(separator = "\n"))
}

private fun BufferedReader.readInt() = readLine().toInt()

private fun BufferedReader.readRange(): Pair<Int, Int> {
	return readLine()
		.split(" ")
		.let { it.first().toInt() to it.last().toInt() }
}

fun prefixHashes(str: String, a: Int, mod: Int): LongArray {
	val prefixHashes = LongArray(str.length + 1) { 0 }
	for (i in str.indices) {
		prefixHashes[i + 1] = (prefixHashes[i] * a % mod + str[i].code) % mod
	}
	return prefixHashes
}

fun powers(str: String, a: Int, mod: Int): LongArray {
  val powers = LongArray(str.length + 1) { 1 }
	for (i in 1..str.length) {
		powers[i] = (powers[i - 1] * a) % mod
	}
	return powers
}

fun hash(prefixHashes: LongArray, powers: LongArray, start: Int, end: Int, mod: Int): Long {
 return (prefixHashes[end] + mod - (prefixHashes[start] + powers[end - start]) % mod) % mod
}