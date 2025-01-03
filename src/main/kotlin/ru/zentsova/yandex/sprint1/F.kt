package ru.zentsova.yandex.sprint1

fun main() {
	val line = readln()
	println(if (isPalindrome(line)) "True" else "False")
}

private fun isPalindrome(line: String): Boolean {
	var i = 0
	var j = line.length - 1

	while (i < j) {
		while (!line[i].lowercaseChar().isLetterOrDigit()) {
			i++
		}

		while (!line[j].lowercaseChar().isLetterOrDigit()) {
			j--
		}

		if (line[i].lowercaseChar() != line[j].lowercaseChar())
			return false

		i++
		j--
	}
	return true
}