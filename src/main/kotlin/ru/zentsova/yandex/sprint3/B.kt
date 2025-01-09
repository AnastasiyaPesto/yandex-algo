package ru.zentsova.yandex.sprint3

import java.io.BufferedReader
import java.io.InputStreamReader

private val DICT = mapOf(
	'2' to "abc",
	'3' to "def",
	'4' to "ghi",
	'5' to "jkl",
	'6' to "mno",
	'7' to "pqrs",
	'8' to "tuv",
	'9' to "wxyz"
)

fun main() {
	val reader = BufferedReader(InputStreamReader(System.`in`))
	val numbers = reader.readStr()

	if (numbers.length == 1)
		println(DICT[numbers[0]])

	generateCombination(numbers, 0, "")
}

private fun generateCombination(numbers: String, i: Int, currentWord: String) {
	if (i == numbers.length) {
		print("$currentWord ")
		return
	}
	val btnStr = DICT[numbers[i]]
	for (j in 0 until btnStr!!.length) {
		generateCombination(numbers, i + 1, currentWord + btnStr[j])
	}
}

private fun BufferedReader.readStr() = this.readLine()