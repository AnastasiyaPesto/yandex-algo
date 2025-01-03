package ru.zentsova.yandex.sprint3

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
	val reader = BufferedReader(InputStreamReader(System.`in`))
	reader.readInt()
	val moneybox = reader.readInts()
	val bikePrice = reader.readInt()
	val doubleBikePrice = 2 * bikePrice

	val day1 = binarySearch(moneybox, doubleBikePrice)
	val day2 = binarySearch(moneybox, doubleBikePrice)
	println("${if (day1 == -1) -1 else day1 + 1} ${if (day2 == -1) -1 else day2 + 1}")

}

fun binarySearch(arr: List<Int>, x: Int): Int {
	if (arr.last() < x) {
		return -1
	}

	var left = 0
	var right = arr.size - 1

	while (left < right) {
		// if (left == mid)
		if (right - left == 1) {
			right = if (arr[left] >= x) left else right
			break
		}

		val mid = left + (right - left) / 2
		if (arr[mid] >= x) {
			right = mid
		} else {
			left = mid
		}
	}

	return right
}

private fun BufferedReader.readInts() = this.readLine().split(" ").map { it.toInt() }

private fun BufferedReader.readInt() = this.readLine().toInt()