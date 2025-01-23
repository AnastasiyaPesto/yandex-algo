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

	val letterPairs = mutableMapOf<Char, Char>()
	var i = 0
	while (i <= str1.lastIndex) {
		val letter1 = str1[i]
		val letter2 = str2[i]
		if (letterPairs.containsKey(letter1)) {
			val opposite = letterPairs[letter1]
			if (letter2 != opposite) return false
		} else if (letterPairs.containsKey(letter2)) {
			val opposite = letterPairs[letter2]
			if (letter1 != opposite) return false
		} else {
			letterPairs[letter1] = letter2
			letterPairs[letter2] = letter1
		}
		i++
	}

	return true
}
