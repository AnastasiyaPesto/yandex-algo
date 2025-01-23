package ru.zentsova.yandex.sprint4

fun main() {
	val n = readln().toInt()
	val classes = mutableSetOf<String>()
	repeat(n) {
		val str = readln()
		classes.add(str)
	}
	classes.forEach {
		println(it)
	}
}