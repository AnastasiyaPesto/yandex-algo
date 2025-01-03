package ru.zentsova.yandex.sprint1

// https://contest.yandex.ru/contest/22449/problems/D/ - Хаотичность погоды

fun main() {
  val n = readInt()
  val arr = readInts()
  val chaosFactor = getChaosFactor(n, arr)
  println(chaosFactor)
}

private fun getChaosFactor(n: Int, arr: List<Int>): Int {
  if (arr.size == 1) return 1
  var res = 0

  for (i in 1..n - 2) {
    if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) res++
  }
  if (arr[0] > arr[1]) res++
  if (arr[n - 1] > arr[n - 2]) res++
  return res
}

private fun readStr() = readln()
private fun readInt() = readStr().toInt()
private fun readInts() = readln().split(" ").map { it.toInt() }