package ru.zentsova.yandex.sprint3

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
	val reader = BufferedReader(InputStreamReader(System.`in`))
	val n = reader.readInt()
	val outputBuffer = StringBuilder()
	generateBracketSequence(str = "", open = 0, close = 0, n = n)
}

private fun generateBracketSequence(str: String, open: Int, close: Int, n: Int) {
	if (str.length == 2 * n) {
		println(str)
	}

	if (open < n) {
		generateBracketSequence("$str(", open + 1, close, n)
	}

	if (close < open) {
	  generateBracketSequence("$str)", open, close + 1, n)
	}
}

private fun BufferedReader.readInt() = this.readLine().toInt()