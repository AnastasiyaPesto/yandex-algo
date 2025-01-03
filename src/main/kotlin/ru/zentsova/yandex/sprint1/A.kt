package ru.zentsova.yandex.sprint1

fun main() {
	val (a, x, b, c) = readInts()
	println(calc(a, b, c, x))
}

private fun calc(a: Int, b: Int, c: Int, x: Int): Int = a * x * x + b * x + c

private fun readInts() = readln().split(" ").map { it.toInt() }