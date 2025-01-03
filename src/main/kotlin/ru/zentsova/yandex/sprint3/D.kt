package ru.zentsova.yandex.sprint3

fun main() {
	/*
	* 0 < n <= 10^4
	* 0 < m <= 10^4
	* 0 < childrenGreedFactor[i] <= 10^3
	* 0 < cookies[i] <= 10^3
	*/
	val n = readInt()
	val childrenGreedFactors = readIntArray()
	val m = readInt()
	val cookies = readIntArray()
	println(numberSatisfiedChildren(childrenGreedFactors, cookies))
}

fun numberSatisfiedChildren(childrenFactors: IntArray, cookies: IntArray): Int {
	var count = 0

	val childrenFactorsSorted = mergeSort(childrenFactors)
	val cookiesSorted = mergeSort(cookies)

	var i = 0
	var j = 0
	while (i < childrenFactorsSorted.size && j < cookiesSorted.size) {
		if (childrenFactorsSorted[i] <= cookiesSorted[j]) {
			count++
			i++
		}
		j++
	}

	return count
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