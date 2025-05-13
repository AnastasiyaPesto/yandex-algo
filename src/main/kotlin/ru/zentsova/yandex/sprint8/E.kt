package ru.zentsova.yandex.sprint8

// E. Вставка строк
fun main() {
	val s = readln()
	val n = readln().toInt()
	val insertions = Array(s.length + 1) { mutableListOf<String>() }

	repeat(n) {
		val (t, kStr) = readln().split(" ")
		val k = kStr.toInt()
		insertions[k].add(t)
	}

	val result = StringBuilder()

	for (i in s.indices) {
		// вставки перед s[i]
		for (str in insertions[i]) {
			result.append(str)
		}
		// текущий символ
		result.append(s[i])
	}

	// возможны вставки в самый конец
	for (str in insertions[s.length]) {
		result.append(str)
	}

	println(result.toString())
}