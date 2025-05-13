package ru.zentsova.yandex.sprint8

// K. Сравнить две строки
fun main() {
	val a = readLine()!!
	val b = readLine()!!

	val filteredA = filterEvenAlphabetLetters(a)
	val filteredB = filterEvenAlphabetLetters(b)

	println(
		when {
			filteredA < filteredB -> -1
			filteredA > filteredB -> 1
			else -> 0
		}
	)
}

fun filterEvenAlphabetLetters(s: String): String {
	val result = StringBuilder()
	for (c in s) {
		// Позиция в алфавите: (c - 'a' + 1)
		if ((c - 'a' + 1) % 2 == 0) {
			result.append(c)
		}
	}
	return result.toString()
}
