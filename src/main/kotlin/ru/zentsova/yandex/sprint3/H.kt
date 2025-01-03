package ru.zentsova.yandex.sprint3

fun main() {
	readInt()
	val array = readInts()
	println(makeBiggestNumber(array))
}

private fun makeBiggestNumber(array: IntArray): String {
	insertionSortByComparator(array, ::comparator)
	return array.joinToString("")
}

private fun insertionSortByComparator(array: IntArray, needMoveLeft: (Int, Int) -> Boolean) {
	for (i in 1 until array.size) {
		val itemToInsert = array[i]
		var j = i
		while (j > 0 && needMoveLeft(array[j - 1], itemToInsert)) {
			array[j] = array[j - 1]
			j--
		}
		array[j] = itemToInsert
	}
}

private fun comparator(num1: Int, num2: Int): Boolean {
	val num12 = num1.toString() + num2.toString()
	val num21 = num2.toString() + num1.toString()
	return num12 < num21
}

private fun readStr() = readln()

private fun readInt() = readln().toInt()

private fun readStrings() = readStr().split(" ")

private fun readInts() = readStrings().map { it.toInt() }.toIntArray()