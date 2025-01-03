package ru.zentsova.yandex.sprint2

fun main() {
	val n = readInt()
	println(fibonacci(n))
}

fun fibonacci(n: Int): Int =
	if (n == 1 || n == 0) 1 else fibonacci(n - 1) + fibonacci( n - 2)

private fun readInt() = readln().toInt()