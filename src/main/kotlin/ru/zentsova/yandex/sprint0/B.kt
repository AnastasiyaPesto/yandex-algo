package ru.zentsova.yandex.sprint0

fun main() {
	val size = readInt()
	val a = readInts()
	val b = readInts()
	val bigArray = zip(a, b, size)
	println(bigArray.joinToString(" "))
}

private fun zip(a: List<Int>, b: List<Int>, size: Int): List<Int> {
	val result = mutableListOf<Int>()
	for (i in 0 until size) {
		result.add(a[i])
		result.add(b[i])
	}
	return result
}

private fun readInt() = readStr().toInt()
private fun readStr() = readln()
private fun readStrings() = readStr().split(" ")
private fun readInts() = readStrings().map { it.toInt() }