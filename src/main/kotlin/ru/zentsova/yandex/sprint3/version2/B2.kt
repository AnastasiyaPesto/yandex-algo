package ru.zentsova.yandex.sprint3.version2

private val dictionary = listOf("", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz")

fun main() {
	val digits = readln()
	val result = mutableListOf<String>()
	allLettersCombinations(digits, dictionary, result, mutableListOf())
	print(result.joinToString(separator = " "))
}

fun allLettersCombinations(digits: String, dictionary: List<String>, result: MutableList<String>, current: MutableList<String>) {
 	if (current.size == digits.length) {
		result.add(current.joinToString(separator = ""))
		return
	}

	val lettersOnButton = dictionary[digits[current.size] - '0']
	for (letter in lettersOnButton) {
		current.add(letter.toString())
		allLettersCombinations(digits, dictionary, result, current)
		current.removeLast()
	}
}
