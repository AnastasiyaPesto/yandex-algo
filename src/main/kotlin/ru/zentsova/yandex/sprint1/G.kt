package ru.zentsova.yandex.sprint1

// https://contest.yandex.ru/contest/22449/problems/G/ - Работа из дома

import java.lang.StringBuilder

fun main() {
  val number = readInt()
  println(toBinary(number))
}

private fun toBinary(number: Int): String {
  if (number == 0 || number == 1) return number.toString()
  val binaryReverse = StringBuilder()
  var res = number
  do {
    binaryReverse.append(res % 2)
    res /= 2
  } while (res > 1)
  binaryReverse.append(res % 2)
  return binaryReverse.reverse().toString()
}

private fun readInt() = readln().toInt()