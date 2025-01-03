package ru.zentsova.yandex.sprint1

fun main() {
	val n = readInt()
	val digits = readInts()
	val k = readInt()
	val result = longAdd(digits, k)
	println(result.joinToString(" "))
}

private fun longAdd(digits: List<Int>, k: Int): List<Int> {
	var kk = digits.reversed().toMutableList()
	var xx = buildList {
		k.toString().forEach {
			add(it.digitToInt())
		}
	}.reversed().toMutableList()

	if (xx.size > kk.size) {
		val swap = kk
		kk = xx
		xx = swap
	}

	var carry = 0
	for (i in kk.indices) {
		val x = if (i < xx.size) xx[i] else 0
		kk[i] += x + carry
		carry = if (kk[i] >= 10) 1 else 0
		kk[i] %= 10
	}

	if (carry > 0) {
		kk.add(carry)
	}

	return kk.reversed()
}


private fun readStr() = readln()
private fun readInt() = readStr().toInt()
private fun readStrings() = readStr().split(" ")
private fun readInts() = readStrings().map { it.toInt() }