package ru.zentsova.yandex.sprint7

// F. Прыжки по лестнице
fun main() {
  val (n, k) = readInts()
  println(stairsUpWaysNumber(n, k))
}

fun stairsUpWaysNumber(n: Int, k: Int): Int {
  val dp = IntArray(n) { 0 }
  dp[0] = 1
  for (i in 1 until n) {
    for (j in maxOf(0, i - k) until i) {
      dp[i] = (dp[i] + dp[j]) % 1000000007
    }
  }
  return dp.last()
}

private fun readInts() = readln().split(" ").map { it.toInt() }
