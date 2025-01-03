package ru.zentsova.yandex.sprint3.version2

import java.util.LinkedList
import java.util.Queue

private val dictionary = listOf("", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz")

fun main() {
	val digits = readln()
	val result = mutableListOf<String>()
	allLettersCombinations(digits, dictionary, result, mutableListOf())
	print(result.joinToString(separator = " "))
//	val combinations = combine(digits, dictionary)
//	print(combinations.joinToString(separator = " "))
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

fun combine(digits: String, dictionary: List<String>): Queue<String> {
	val combinations: Queue<String> = LinkedList()
	combinations.add("")
	for (i in 0 until digits.length) {
		while (combinations.peek().length == i) {
			val lettersOnButton = dictionary[digits[i] - '0']
			for (letter in lettersOnButton) {
				combinations.add(combinations.peek() + letter)
			}
			combinations.poll()
		}
	}
	return combinations
}
