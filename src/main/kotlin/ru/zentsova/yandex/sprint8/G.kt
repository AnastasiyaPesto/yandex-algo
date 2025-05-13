package ru.zentsova.yandex.sprint8

// G. Поиск со сдвигом
fun main() {
	readInt()
	val x = readInts()
	readInt()
	val a = readInts()

	val result = findPatternMatchesWithShift(x, a)
	println(result.joinToString(" "))
}

fun findPatternMatchesWithShift(x: List<Int>, a: List<Int>): List<Int> {
	val n = x.size
	val m = a.size

	// Один элемент в шаблоне — любое значение с нужным сдвигом подходит
	if (m == 1) return (1..n).toList()

	// Вычисляем разности между соседними элементами шаблона
	val patternDiff = IntArray(m - 1) { i -> a[i + 1] - a[i] }

	val result = mutableListOf<Int>()

	for (i in 0..(n - m)) {
		var match = true
		for (j in 0 until m - 1) {
			if (x[i + j + 1] - x[i + j] != patternDiff[j]) {
				match = false
				break
			}
		}
		if (match) {
			result.add(i + 1) // индексация с 1
		}
	}

	return result
}

private fun readInt() = readln().toInt()
private fun readInts() = readln().split(" ").map { it.toInt() }