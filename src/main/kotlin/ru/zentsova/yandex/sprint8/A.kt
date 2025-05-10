package ru.zentsova.yandex.sprint8

// A. Разворот строки
fun main() {
  val line = readln()
	println(reverseWords(line))
}

fun reverseWords(text: String): String {
	val words = Array(1000) { CharArray(100) } // Хранилище слов (до 1000 слов по 100 символов)
	val lengths = IntArray(1000)               // Длины слов
	var wordCount = 0
	var charIndex = 0

	// Разбиение строки на слова
	for (i in text.indices) {
		val c = text[i]
		if (c != ' ') {
			words[wordCount][charIndex] = c
			charIndex++
		} else {
			lengths[wordCount] = charIndex
			wordCount++
			charIndex = 0
		}
	}

	// Добавляем последнее слово
	lengths[wordCount] = charIndex
	wordCount++

	// Собираем результат в обратном порядке
	val result = CharArray(text.length)
	var pos = 0

	for (i in wordCount - 1 downTo 0) {
		for (j in 0 until lengths[i]) {
			result[pos++] = words[i][j]
		}
		if (i != 0) {
			result[pos++] = ' '
		}
	}

	return String(result, 0, pos)
}