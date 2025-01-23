package ru.zentsova.yandex.sprint4

import kotlin.math.max

// B. Соревнования
fun main() {
	val n = readInt()
	if (n == 0) {
		println("0")
		return
	}
	val rounds = readIntArray()
	val largestSegment = largestContinuousSegment(rounds)
	print(largestSegment)
}

fun largestContinuousSegment(rounds: IntArray): Int {
	val statistics = IntArray(rounds.size + 1) { 0 }
	var tmp = 0
	for (i in 0..rounds.lastIndex) {
		if (rounds[i] == 0) tmp++ else tmp--
		statistics[i + 1] = tmp
	}

	var result = 0
  val leftBorders = mutableMapOf<Int, Int>()
	statistics.forEachIndexed { idx, value ->
		if (leftBorders.containsKey(value)) {
			result = max(result, idx - leftBorders.getValue(value))
		} else {
			leftBorders[value] = idx
		}
	}

	return result
}

private fun readInt() = readStr().toInt()
private fun readStr() = readln()
private fun readStrings() = readStr().split(" ")
private fun readIntArray() = readStrings().map { it.toInt() }.toIntArray()