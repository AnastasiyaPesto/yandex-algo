package ru.zentsova.yandex.sprint0

fun main() {
	readInt()
	val a = readInts()
	val k = readInt()
	println(movingAverage(a, k).joinToString(" "))
}

private fun movingAverage(a: List<Int>, k: Int): List<Double> {
	val result = mutableListOf<Double>()

	var currentSum = 0
	for (i in 0 until k) {
		currentSum += a[i]
	}
	result.add(currentSum / k.toDouble())

	for (i in 0 until a.size - k) {
		currentSum -= a[i]
		currentSum += a[i + k]
		result.add(currentSum / k.toDouble())
	}

	return result
}

private fun readStr() = readln()
private fun readInt() = readStr().toInt()
private fun readStrings() = readStr().split(" ")
private fun readInts() = readStrings().map { it.toInt() }