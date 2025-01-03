package ru.zentsova.yandex.sprint1

import kotlin.math.abs

fun main() {
	val (a, b, c) = readInts()
	val ok = solve(a, b, c)
	if (ok) {
		println("WIN")
	} else {
		println("FAIL")
	}
}

private fun solve(a: Int, b: Int, c: Int): Boolean {
  val res = abs(a % 2) + abs(b % 2) + abs(c % 2)
	return (res == 0 || res == 3)
}

private fun readStr() = readln()
private fun readStrings() = readStr().split(" ")
private fun readInts() = readStrings().map { it.toInt() }