package ru.zentsova.yandex.sprint0

fun main() {
	// 1 <= n <= 10_000
 	val n = readInt()
	val arr = readInts()
	val k = readInt()
	val arrAvg = movingAverage(arr, n, k)
	println(arrAvg.joinToString(" "))
}

private fun movingAverage(arr: List<Int>, n: Int, k: Int): List<Double> {
	return buildList {
		var curSum = 0
		for (i in 0 until k) {
			curSum += arr[i]
		}
		add(curSum / k.toDouble())
		for (i in 1..(n-k)) {
			curSum -= arr[i - 1]
			curSum += arr[i + k - 1]
			add(curSum / k.toDouble())
		}
	}
}

private fun readInt() = readStr().toInt()
private fun readStr() = readln()
private fun readStrings() = readStr().split(" ")
private fun readInts() = readStrings().map { it.toInt() }