package ru.zentsova.yandex.sprint3

fun main() {
	if (readInt() == 0) return

	val sorted = countingSort(readInts())
	println(sorted.joinToString(" "))
}

private fun countingSort(array: IntArray, maxValue: Int = 3): IntArray {
	val result = IntArray(maxValue) { 0 }
	for (value in array) {
		result[value]++
	}

	var index = 0
	for (value in 0 until maxValue) {
		for (i in 0 until result[value]) {
			array[index] = value
			index++
		}
	}

	return array
}

private fun readInt() = readln().toInt()

private fun readInts() = readln().split(" ").map { it.toInt() }.toIntArray()