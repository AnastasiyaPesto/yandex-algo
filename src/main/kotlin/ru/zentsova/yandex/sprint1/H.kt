package ru.zentsova.yandex.sprint1

// https://contest.yandex.ru/contest/22449/problems/H/?success=104101690#2989/2020_04_13/vTLkp8umJC - H. Двоичная система
import kotlin.text.StringBuilder

fun main() {
	val number1 = readln()
	val number2 = readln()
	println(getSum(number1, number2))
}

private fun getSum(firstNumber: String, secondNumber: String): String {
	var longerNumber = StringBuilder(firstNumber)
	var shorterNumber = StringBuilder(secondNumber)

	if (firstNumber.length < secondNumber.length) {
		longerNumber = StringBuilder(secondNumber)
		shorterNumber = StringBuilder(firstNumber)
	}

	for (i in 0 until (longerNumber.length - shorterNumber.length)) {
		shorterNumber.insert(0, "0")
	}

	var carry = 0
	val sb = StringBuilder()
	for (i in longerNumber.length - 1 downTo 0) {
		var c = longerNumber[i].digitToInt() + shorterNumber[i].digitToInt() + carry
		if (c > 1) {
			c -= 2
			carry = 1
		} else {
			carry = 0
		}
		sb.insert(0, c)
	}

	if (carry == 1) sb.insert(0, 1)

	return sb.toString()
}
