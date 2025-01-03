package ru.zentsova.yandex.sprint0

private fun getTwoSum(a: List<Int>, k: Int): Pair<Int, Int>? {
  for (i in a.indices) {
		for (j in i + 1 until a.size) {
			if (a[i] + a[j] == k) {
				return a[i] to a[j]
			}
		}
	}

	return null
}

fun main() {
	readInt()
	val a = readInts()
	val k = readInt()
	val result = getTwoSum(a, k)
	if (result == null) {
		println("None")
	} else {
		println("${result.first} ${result.second}")
	}
}

private fun readStr() = readln()
private fun readInt() = readStr().toInt()
private fun readStrings() = readStr().split(" ")
private fun readInts() = readStrings().map { it.toInt() }