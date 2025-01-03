package ru.zentsova.yandex.sprint3.version2

import java.lang.StringBuilder

fun main() {
	val firstLine = readln()
	val secondLine = readln()
	println(if (subsequence(firstLine, secondLine)) "True" else "False")
}

fun subsequence(firstLine: String, secondLine: String): Boolean {
	if (firstLine.length > secondLine.length) return false

	var j = 0
	val tmp = StringBuilder()
	for (letter in firstLine) {
		while (j < secondLine.length) {
			if (letter == secondLine[j]) {
				tmp.append(letter)
				j++
				break
			}
			j++
		}
	}

  return tmp.toString() == firstLine
}