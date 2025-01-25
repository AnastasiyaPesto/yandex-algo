package ru.zentsova.yandex.sprint4

// C. Странное сравнение
fun main() {
	val reader = System.`in`.bufferedReader()
	val str1 = reader.readLine()
	val str2 = reader.readLine()
	if (compare(str1, str2))
		println("YES")
	else
		println("NO")
}

fun compare(str1: String, str2: String): Boolean {
	if (str1.length != str2.length) return false

	val letterPairs1 = mutableMapOf<Char, Char>()
	val letterPairs2 = mutableMapOf<Char, Char>()
	for (i in str1.indices) {
		val l1 = str1[i]
		val l2 = str2[i]

		if (!letterPairs1.containsKey(l1) && !letterPairs2.containsKey(l2)) {
			letterPairs1[l1] = l2
			letterPairs2[l2] = l1
		}

		if ((letterPairs1.containsKey(l1) && letterPairs1.getValue(l1) != l2)
			   || (letterPairs2.containsKey(l2) && letterPairs2.getValue(l2) != l1))
		{
			return false
		}
	}

	return true
}
