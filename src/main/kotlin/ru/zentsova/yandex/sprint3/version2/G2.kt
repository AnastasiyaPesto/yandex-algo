package ru.zentsova.yandex.sprint3.version2

fun main() {
	if (readInt() > 0) {
		val array = readIntArray()
		val result = wardrobe(array)
		print(result.joinToString(separator = " "))
	}
}

// Dutch national flag problem
// https://www.geeksforgeeks.org/sort-an-array-of-0s-1s-and-2s/
// https://en.wikipedia.org/wiki/Dutch_national_flag_problem
fun wardrobe(array: IntArray): IntArray {
	var k = 0
	var i = 0
	var j = array.size - 1

	while (k <= j) {
		when (array[k]) {
			0 -> {
				swap(array, i, k)
				i++
				k++
			}
			1 -> {
				k++
			}
			2 -> {
				swap(array, j, k)
				j--
			}
		}
	}

	return array
}
fun swap(array: IntArray, i: Int, j: Int) {
	val tmp = array[i]
	array[i] = array[j]
	array[j] = tmp
}

private fun readInt() = readln().toInt()

private fun readIntArray() = readln().split(" ").map { it.toInt() }.toIntArray()