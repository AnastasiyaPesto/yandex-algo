package ru.zentsova.yandex.sprint1

fun main() {
	val simpleMultipliers = simpleMultipliers(readInt())
	println(simpleMultipliers.joinToString(" "))
}

fun simpleMultipliers(n: Int): List<Int> {
	return buildList {
		var checkedNumber = n
		var number = 2
		while (number * number <= n) {
			if (checkedNumber % number == 0) {
				checkedNumber /= number
				add(number)
			} else {
				number++
			}
		}
		if (checkedNumber > 1) {
			add(checkedNumber)
		}
	}
}

private fun readStr() = readln()

private fun readInt() = readStr().toInt()