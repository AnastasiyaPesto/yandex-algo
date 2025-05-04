package ru.zentsova.yandex.sprint7

// E. Алла на Алгосах
fun main() {
  val x = readInt()
  readInt()
	val nominals = readInts()
	println(minCoins(nominals, x))
}


fun minCoins(nominals: IntArray, totalSum: Int): Int {
	val dp = IntArray(totalSum + 1) { Int.MAX_VALUE }
	dp[0] = 0

	for (currentSum in 1..totalSum) {
		for (nominal in nominals) {
			if (nominal <= currentSum && dp[currentSum - nominal] != Int.MAX_VALUE) {
				dp[currentSum] = minOf(dp[currentSum], dp[currentSum - nominal] + 1)
			}
		}
	}

	return if (dp[totalSum] == Int.MAX_VALUE) -1 else dp[totalSum]
}

private fun readInt() = readln().toInt()
private fun readInts() = readln().split(" ").map { it.toInt() }.toIntArray()