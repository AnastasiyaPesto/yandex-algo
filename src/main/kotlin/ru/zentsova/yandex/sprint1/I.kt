package ru.zentsova.yandex.sprint1

fun main() {
	val number = readInt()
	println(if (isDegreeOfFour(number)) "True" else "False")
}

private fun isDegreeOfFour(n: Int): Boolean {
	var num = n
	while (num % 4 == 0) {
		num /= 4
	}
	return num == 1
}

private fun isDegreeOfFour2(n: Int): Boolean {
	// деление на цело на N: сдвиг вправо на X (2^X = N)
	// 12 = 1100 >> 2 (бита) = 11 = 3

	// Число делится на 2 без остатка, если в двоичной системе 1 последний разряд == 0
	// Число делится на 4 без остатка, если в двоичной системе 2 последних разрядя == 0
	// Число делится на 8 без остатка, если в двоичной системе 3 последних разрядя == 0
	// Побитовое &
	// num & 1 => останется последний разряд (бит)
	// num & 2 => останется 2 последних разряда (бита)
	// Пример: 12 = 1100
	// 1100
	// 0001
	// 0000
	var num = n
	while (((num and 1) == 0) && (((num shr 1) and 1) == 0)) {
		num = num shr 2
	}
	return num == 1
}

private fun readStr() = readln()
private fun readInt() = readStr().toInt()