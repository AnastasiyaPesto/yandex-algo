package ru.zentsova.yandex.sprint3.version2

fun main() {
	val n = readln().toInt()
	val str = mutableListOf<String>()
	generateBracketSequence(str, n, 0)
}

private fun generateBracketSequence(str: MutableList<String>, open: Int, close: Int) {
	if (open == 0 && close == 0) {
		println(str.joinToString(separator = ""))
		return
	}

	if (open > 0) {
		str.add("(")
		generateBracketSequence(str, open - 1, close + 1)
		str.removeLast()
	}

	if (close > 0) {
		str.add(")")
		generateBracketSequence(str, open, close - 1)
		str.removeLast()
	}
}
