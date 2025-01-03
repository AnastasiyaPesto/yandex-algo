package ru.zentsova.yandex.sprint3

fun main() {
	val (houseCount, money) = info()
	val houseCosts = readIntArray()
	val result = largestNumberHousesToBuy(houseCosts, money)
	print(result)
}

private fun info(): Pair<Int, Int> {
	val input = readInts()
	return input[0] to input[1]
}

fun largestNumberHousesToBuy(houses: IntArray, money: Int): Int {
	val housesSorted = mergeSort(houses)

	var result = 0
	var i = 0
	var m = money
	while (i < housesSorted.size) {
		m -= housesSorted[i++]
		if (m < 0) break
		result++
	}

	return result
}

private fun mergeSort(array: IntArray): IntArray {
	if (array.size < 2) return array

	val left = mergeSort(array.sliceArray(0 until array.size / 2))
	val right = mergeSort(array.sliceArray(array.size / 2 until array.size))

	val result = IntArray(array.size)

	var l = 0
	var r = 0
	var i = 0
	while (l < left.size && r < right.size) {
		if (left[l] <= right[r]) {
			result[i] = left[l]
			l++
		} else {
			result[i] = right[r]
			r++
		}
		i++
	}

	while (l < left.size) {
		result[i] = left[l]
		i++
		l++
	}

	while (r < right.size) {
		result[i] = right[r]
		i++
		r++
	}

	return result
}

private fun readInt() = readStr().toInt()
private fun readStr() = readln()
private fun readStrings() = readStr().split(" ")
private fun readInts() = readStrings().map { it.toInt() }
private fun readIntArray() = readInts().toIntArray()