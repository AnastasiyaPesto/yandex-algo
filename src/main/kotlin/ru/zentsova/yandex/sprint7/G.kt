package ru.zentsova.yandex.sprint7

// G. Банкомат
fun main() {
	val m = readInt()
	readInt()
	val nominals = readInts()
	println(count(nominals, m))
}

fun count(nominals: IntArray, totalSum: Int): Int {
	val dp = IntArray(totalSum + 1) { 0 }
	dp[0] = 1

	for (nominal in nominals) {
		for (currentSum in nominal .. totalSum) {
			dp[currentSum] += dp[currentSum - nominal]
		}
	}
	return dp[totalSum]
}

private fun readInt() = readln().toInt()
private fun readInts() = readln().split(" ").map { it.toInt() }.toIntArray()