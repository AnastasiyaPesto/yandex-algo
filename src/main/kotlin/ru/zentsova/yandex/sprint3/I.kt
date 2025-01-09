package ru.zentsova.yandex.sprint3

fun main() {
	readInt()
	val array = readInts()
	val k = readInt()
	val sorted = conferenceLovers(array)
	val result = if (sorted.size > k) {
		sorted.subList(0, k)
	} else {
		sorted
	}
	print(result.map { it.first }.joinToString(separator = " "))
}

fun conferenceLovers(array: List<Int>): List<Pair<Int, Int>> = statistics(array).sort()

private fun statistics(array: List<Int>): List<Pair<Int, Int>> {
	val counter = mutableMapOf<Int, Int>()
	array.forEach { universityId ->
		counter.compute(universityId) { _, quantity ->
			quantity?.let { it + 1 } ?: 1
		}
	}
	return counter.map { it.key to it.value }
}

private fun List<Pair<Int, Int>>.sort() = sortedWith { lhs, rhs ->
	if (lhs.second == rhs.second) {
		if (lhs.first < rhs.first) -1 else 1
	} else {
		if (lhs.second > rhs.second) -1 else 1
	}
}

private fun readInt() = readln().toInt()

private fun readInts() = readln().split(" ").map { it.toInt() }
