package ru.zentsova.yandex.sprint3

fun merge(arr: IntArray, left: Int, mid: Int, right: Int): IntArray {
	val result = IntArray(right - left)
	var l = left
	var r = mid
	var k = 0
	while (l < mid && r < right) {
		if (arr[l] <= arr[r]) {
			result[k] = arr[l]
			l++
		} else {
			result[k] = arr[r]
			r++
		}
		k++
	}
	while (l < mid) {
		result[k] = arr[l]
		l++
		k++
	}
	while (r < right) {
		result[k] = arr[r]
		r++
		k++
	}

	return result
}

fun mergeSort(arr: IntArray, left: Int, right: Int) {
	if (right - left <= 1) {
		return
	}

	val mid = left + (right - left) / 2
	mergeSort(arr = arr, left = left, right = mid)
	mergeSort(arr = arr, left = mid, right = right)

	val mergedArr = merge(arr, left, mid, right)
	var j = 0
	for (i in left until right) {
		arr[i] = mergedArr[j++]
	}
}

fun test() {
	val a = intArrayOf(1, 4, 9, 2, 10, 11)
	val b: IntArray = merge(a, 0, 3, 6)
	val expected = intArrayOf(1, 2, 4, 9, 10, 11)
	assert(b.contentEquals(expected))
	val c = intArrayOf(1, 4, 2, 10, 1, 2)
	mergeSort(c, 0, 6)
	val expected2 = intArrayOf(1, 1, 2, 2, 4, 10)
	assert(c.contentEquals(expected2))
}

fun main() {
	test()
}