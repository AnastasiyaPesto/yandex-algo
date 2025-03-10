package ru.zentsova.yandex.sprint5

// I. Разные деревья поиска

fun main() {
	val n = readln().toInt()
	val bstCount = countBinarySearchTrees(n)
	println(bstCount)
}

fun countBinarySearchTrees(n: Int): Int {
	if (n <= 1) return 1

	var bstCount = 0
	for (i in 1..n) {
		val left = countBinarySearchTrees(i - 1)
		val right = countBinarySearchTrees(n - i)

		bstCount += left * right
	}
	return bstCount
}