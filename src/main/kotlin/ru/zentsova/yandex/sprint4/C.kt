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

	val letterPairs12 = mutableMapOf<Char, Char>()
	val letterPairs21 = mutableMapOf<Char, Char>()
	for (i in str1.indices) {
		val l1 = str1[i]
		val l2 = str2[i]

		if (!letterPairs12.containsKey(l1) && !letterPairs21.containsKey(l2)) {
			letterPairs12[l1] = l2
			letterPairs21[l2] = l1
			continue
		}

		if ((letterPairs12.containsKey(l1) && letterPairs12.getValue(l1) != l2)
			   || (letterPairs21.containsKey(l2) && letterPairs21.getValue(l2) != l1))
		{
			return false
		}
	}

	return true
}
