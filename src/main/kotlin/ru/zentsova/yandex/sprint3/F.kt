package ru.zentsova.yandex.sprint3

fun main() {
  readInt()
	val array = readIntArray()
	println(largestTrianglePerimeter(array))
}

fun largestTrianglePerimeter(array: IntArray): Int {
	val arraySorted = reverseMergeSort(array)

	var i = 0
	var j = 1
	var k = 2
	while (k < arraySorted.size) {
		if (arraySorted[i] < arraySorted[j] + arraySorted[k]) {
			return arraySorted[i] + arraySorted[j] + arraySorted[k]
		}
		i++
		j++
		k++
	}

	return 0
}

private fun reverseMergeSort(array: IntArray): IntArray {
	if (array.size < 2) return array

	val left = reverseMergeSort(array.sliceArray(0 until array.size / 2))
	val right = reverseMergeSort(array.sliceArray(array.size / 2 until array.size))

	val result = IntArray(array.size)

	var l = 0
	var r = 0
	var i = 0
	while (l < left.size && r < right.size) {
		if (left[l] >= right[r]) {
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