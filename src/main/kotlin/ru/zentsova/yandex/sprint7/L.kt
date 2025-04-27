package ru.zentsova.yandex.sprint7

// L. Золото лепреконов
fun main() {
  val (_, m) = readInts()
	val weights = readWeights()
	println(maxWeight(weights, m))
}

fun maxWeight(weights: IntArray, capacity: Int): Int {
  val dp = IntArray(capacity + 1)
	dp[0] = 1
	var max = 0
	for (weight in weights) {
		for (i in capacity downTo weight) {
			if (dp[i - weight] == 1) {
				dp[i] = 1
				max = maxOf(max, i)
			}
		}
	}

	return max
}

private fun readInts() = readln().split(" ").map { it.toInt() }
private fun readWeights() = readInts().toIntArray()
