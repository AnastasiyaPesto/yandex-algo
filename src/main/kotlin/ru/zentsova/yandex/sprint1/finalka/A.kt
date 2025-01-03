package ru.zentsova.yandex.sprint1.finalka

import kotlin.math.min

fun main() {
	readInt()
	val nums = readInts()
	val distances = distances(nums)
	println(distances.joinToString(" "))
}

private fun distances(nums: List<Int> ): List<Int> {
	val res = mutableListOf<Int>()
	directIteration(res, nums)
	reverseIteration(res)
	return res
}

private fun directIteration(res: MutableList<Int>, nums: List<Int> ) {
	var i = 0
	while (nums[i] != 0) {
		res.add(Int.MAX_VALUE)
		i++
	}

	var count = 0
	while (i < nums.size) {
		if (nums[i] == 0) {
			count = 0
		} else {
			count++
		}
		res.add(count)
		i++
	}
}

private fun reverseIteration(res: MutableList<Int>) {
	var i = res.size - 1
	while (res[i] != 0) {
		i--
	}

	var count = 0
	while (i >= 0) {
		if (res[i] != 0) {
			count++
			res[i] = min(res[i], count)
		} else {
			count = 0
		}
		i--
	}
}

private fun readStr() = readln()
private fun readStrings() = readStr().split(" ")

private fun readInt() = readln().toInt()
private fun readInts() = readStrings().map { it.toInt() }