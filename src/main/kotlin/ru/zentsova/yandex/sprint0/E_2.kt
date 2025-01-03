package ru.zentsova.yandex.sprint0

private fun getTwoSumFast(a: List<Int>, k: Int): Pair<Int, Int>? {
  val store = mutableSetOf<Int>()
	a.forEach {
		if (store.contains(k - it)) {
			return k - it to it
		} else {
			store.add(it)
		}
	}
	return null
}

fun main() {
	readInt()
	val a = readInts()
	val k = readInt()
	val result = getTwoSumFast(a, k)
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