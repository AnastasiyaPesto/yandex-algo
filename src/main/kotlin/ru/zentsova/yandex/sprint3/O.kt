package ru.zentsova.yandex.sprint3

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
	val reader = BufferedReader(InputStreamReader(System.`in`))
	reader.readInt()
	val array = reader.readInts()
	val k = reader.readInt()
	array.sort()
	trashIndex(array, k)?.let { print(it) }
}

fun trashIndex(array: IntArray, k: Long): Int? {
	if (array.isEmpty()) return null

	var left = 0
	var right = array.last() - array.first()
	while (left < right) {
		val middle = left + (right - left) / 2
		if (checkIndex(array, middle, k)) {
			right = middle
		} else {
			left = middle + 1
		}
	}
	return left
}

fun checkIndex(array: IntArray, diff: Int, k: Long): Boolean {
	var left = 0
	var count = 0
	for (right in 1 until array.size) {
		while (array[right] - array[left] > diff) {
			left++
		}
		count += right - left
		if (count >= k) return true
	}
	return false
}

private fun BufferedReader.readInt() = readLine().toLong()
private fun BufferedReader.readInts() = readLine().split(" ").map { it.toInt() }.toIntArray()