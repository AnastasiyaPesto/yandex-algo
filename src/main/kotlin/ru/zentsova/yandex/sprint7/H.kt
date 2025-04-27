package ru.zentsova.yandex.sprint7

// H. Поле с цветочками
fun main() {
	val (n, _) = readFieldDimension()
  val points = Array(n) { readInts() }
	println(getMaxPoints(points))
}

fun getMaxPoints(points: Array<IntArray>): Int {
	val n = points.size
	val m = points[0].size
	val dp = Array(n) { IntArray(m) }

	for (i in n - 1 downTo 0) {
		for (j in 0 until m) {
			val down = if (i + 1 < n) dp[i + 1][j] else 0
			val left = if (j - 1 >= 0) dp[i][j - 1] else 0
			dp[i][j] = maxOf(down, left) + points[i][j]
		}
	}
	return dp[0][m - 1]
}

private fun readFieldDimension() = readln().split(" ").map { it.toInt() }
private fun readInts() = readln().map { it.digitToInt() }.toIntArray()
