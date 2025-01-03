package ru.zentsova.yandex.sprint3

fun main() {
	insertionSort(intArrayOf(11, 2, 9, 7, 1))
}

fun insertionSort(array: IntArray) {
	for (i in 1 until array.size) {
		val itemToInsert = array[i]
		var j = i
		while (j > 0 && itemToInsert < array[j - 1]) {
			array[j] = array[j - 1]
			j -= 1
		}
		array[j] = itemToInsert
		println("step $i, sorted ${i + 1} elements: ${array.toList()}")
	}
}