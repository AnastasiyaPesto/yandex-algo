package ru.zentsova.yandex.sprint0

private fun twoSum(arr: MutableList<Int>, k: Int): List<Int>? {
	arr.sort()
	var i = 0
	var j = arr.size - 1
	 while (i < j) {
		 val sum = arr[i] + arr[j]
		 if (sum == k) {
			 return listOf(arr[i], arr[j])
		 } else if (sum < k) {
			 i++
		 } else {
			 j--
		 }
	 }
	return null
}

// -3 0 4 1 9 -5 7    k = 6  -3, 9
private fun twoSum2(arr: MutableList<Int>, k: Int): List<Int>? {
	val set = mutableSetOf<Int>()
	arr.forEach {
		if (set.contains(k - it)) {
			return listOf(it, k - it)
		} else {
			set.add(it)
		}
	}
	return null
}

fun main() {
	readInt()
	val arr = readInts()
	val k = readInt()
	val result = twoSum(arr.toMutableList(), k)
	println(result?.joinToString(" ") ?: "None" )
}

private fun readInt() = readStr().toInt()
private fun readStr() = readln()
private fun readStrings() = readStr().split(" ")
private fun readInts() = readStrings().map { it.toInt() }