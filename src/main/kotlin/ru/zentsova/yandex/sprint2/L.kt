package ru.zentsova.yandex.sprint2

import kotlin.math.pow

fun main() {
	val nk = readInts()
	println(fibonacci(nk[0], nk[1]))
}

private fun fibonacci(n: Int, k: Int): Int {
	val mod = 10.0.pow(k).toInt()

	if (n == 1 || n == 0) return 1

	var prev = 1
	var curr = 1
	for (i in 2..n) {
		val tmp = prev
		prev = curr
		curr = (prev + tmp) % mod
	}

	return curr
}

private fun readStr() = readln()
private fun readStrings() = readStr().split(" ")
private fun readInts() = readStrings().map { it.toInt() }