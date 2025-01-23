package ru.zentsova.yandex.sprint4

import kotlin.random.Random

const val alphabet = "abcdefghijklmnopqrstuvwxyz"

// E. Сломай меня
fun main() {
	val (firstWord, secondWord) = generateEqualByHashWords(source = alphabet, length = 10)
	println(firstWord)
	println(secondWord)
}

fun generateEqualByHashWords(source: String, length: Int): Pair<String, String> {
	val map = HashMap<Int, String>()
	val q = 1000
	val r = 123987123
	var i = 1000000
	while (i > 0) {
		val word = generateWord(alphabet, length)
		val wordHash = polynomialHash(word, q, r)
		if (map.containsKey(wordHash) && map[wordHash] != word) {
			return map.getValue(wordHash) to word
		} else {
			map[wordHash] = word
		}
		i--
	}
	return "" to ""
}

private fun generateWord(source: String, length: Int): String {
	var currentLength = 0
	val word = StringBuilder()
	while (currentLength < length) {
		val randomIndex = Random.nextInt(0, source.length)
		word.append(source[randomIndex])
		currentLength++
	}
	return word.toString()
}
