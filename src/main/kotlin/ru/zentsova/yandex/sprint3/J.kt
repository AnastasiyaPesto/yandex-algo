package ru.zentsova.yandex.sprint3

fun main() {
	val n = readInt()
	val arr = readInts()
	bubbleSort(arr)
}

private fun bubbleSort(array: IntArray): IntArray {
	var isSwapped = false
	var isSwappedOnce = false
	for (i in array.indices) {
		for (j in 0 until array.size - i - 1) {
			if (array[j] > array[j + 1]) {
				val tmp = array[j]
				array[j] = array[j + 1]
				array[j + 1] = tmp
				isSwapped = true
			}
		}
		if (isSwapped) {
			println(array.joinToString(" "))
			isSwappedOnce = true
			isSwapped = false
		}
	}
	if (!isSwappedOnce) {
		println(array.joinToString(" "))
	}
	return array
}

private fun readStr() = readln()

private fun readInt() = readln().toInt()

private fun readStrings() = readStr().split(" ")

private fun readInts() = readStrings().map { it.toInt() }.toIntArray()