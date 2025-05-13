package ru.zentsova.yandex.sprint8

// L. Подсчёт префикс-функции
fun main() {
	val s = readLine()!!
	val prefixFunction = computePrefixFunction(s)
	println(prefixFunction.joinToString(" "))
}

fun computePrefixFunction(s: String): IntArray {
	val n = s.length
	val pi = IntArray(n)

	for (i in 1 until n) {
		var j = pi[i - 1]

		while (j > 0 && s[i] != s[j]) {
			j = pi[j - 1]
		}

		if (s[i] == s[j]) {
			j++
		}

		pi[i] = j
	}

	return pi
}

