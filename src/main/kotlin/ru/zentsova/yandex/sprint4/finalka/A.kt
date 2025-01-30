package ru.zentsova.yandex.sprint4.finalka

import java.io.BufferedReader

fun main() {
	val reader = System.`in`.bufferedReader()
	val documentsCount = reader.readInt()
	// индексы не важны?
	// val searchIndex = mutableMapOf<String, Int>()
	val searchIndex = mutableMapOf<String, MutableSet<Int>>()
	repeat(documentsCount) {
		val string = reader.readLine()
		fillSearchIndex(string, searchIndex)
	}

	val queriesCount = reader.readInt()
	val queries = (1..queriesCount).map { reader.readLine() }
}

private fun BufferedReader.readInt() = readLine().toInt()

private fun fillSearchIndex(string: String, searchIndex: MutableMap<String, MutableSet<Int>>) {
	var i = 0
	var j = 0
	while (i < string.lastIndex && j < string.lastIndex) {
		while (string[j] != ' ') j++
		val cur = string.substring(i, j)
		searchIndex.compute(cur) { _, indexes ->
			(indexes ?: mutableSetOf()).apply { add(i) }
		}
		j++
		i = j
	}
}

private data class Data(
	val serialNumberInDb: Int,
	val indexes: List<Int>
)