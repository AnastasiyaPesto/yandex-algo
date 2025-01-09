package ru.zentsova.yandex.sprint3

import kotlin.Int.Companion.MIN_VALUE

fun main() {
	readInt()
	val array = readInts()
	print(parts(array))
}

fun parts(array: IntArray): Int {
  var parts = 0
	var max = MIN_VALUE

	for (i in array.indices) {
		max = maxOf(max, array[i])
		if (max == i) parts++
	}

	return parts
}

private fun readInt() = readln().toInt()

private fun readInts() = readln().split(" ").map { it.toInt() }.toIntArray()