package ru.zentsova.yandex.sprint1.finalka

import java.lang.Character.getNumericValue
import java.lang.Character.isDigit

private const val MAX_NUM_VALUE_FIELD = 9

fun main() {
	val k = readInt()
	val sb = StringBuilder()
	for (i in 0 until 4) {
		sb.append(readStr())
	}
	val preparedField = processField(sb.toString())
	println(maxPointInPlay(preparedField, k))
}

private fun processField(field: String): IntArray {
	val res = IntArray(MAX_NUM_VALUE_FIELD + 1)
	for (ch in field) {
		if (isDigit(ch)) res[getNumericValue(ch)]++
	}
	return res
}

private fun maxPointInPlay(field: IntArray, k: Int): Int {
	val maxPressedKeyCnt = 2 * k
	var maxPoint = 0
	for (i in field) {
		if (i != 0 && maxPressedKeyCnt >= i) maxPoint++
	}
	return maxPoint
}

private fun readStr() = readln()

private fun readInt() = readln().toInt()