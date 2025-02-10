package ru.zentsova.yandex.sprint4

fun main() {
	val a = readInt()
	val m = readInt()
	val string = readStr()
	val stringHash = polynomialHash(string, a, m)
	print(stringHash)
}

fun polynomialHash(string: String, a: Int, m: Int): Int {
	if (string.isBlank()) return 0

	var hash = string.first().letterCodeLong % m
	for (i in 1..string.lastIndex) {
		hash = (hash * a + string[i].code) % m
	}

	return hash.toInt()
}

val Char.letterCodeLong: Long get() = this.code.toLong()

//fun polyHash(str: String, a: Int, m: Int): Int {
//	var hash = 0
//	for (ch in str) {
//		hash = (hash * a % m + ch.code) % m
//	}
//	return hash
//}

private fun readInt() = readStr().toInt()
private fun readStr() = readln()