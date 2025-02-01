package ru.zentsova.yandex.sprint4.finalka

import java.io.BufferedReader

fun main() {
	val reader = System.`in`.bufferedReader()
	val documentsCount = reader.readInt()
	// индексы не важны?
	// val searchIndex = mutableMapOf<String, Int>()
	val inputDocuments = (1..documentsCount).map {
		countForSameWordsInLine(reader.readLine())
	}

	val queriesCount = reader.readInt()
	val inputQueries = (1..queriesCount).map { reader.readLine() }
}

private fun BufferedReader.readInt() = readLine().toInt()

fun documentRelevance(queries: List<String>, documents: List<Map<String, Int>>): List<Map<Int, Int>> {
	if (documents.isEmpty() || queries.isEmpty()) return emptyList()

	val result = MutableList<MutableMap<Int, Int>>(queries.size) { mutableMapOf() }
	for (i in queries.indices) {
		for (j in documents.indices) {
			val document = documents[j]
			val queryWords = queries[i].split(" ")
			var counter = 0
			for (k in queryWords.indices) {
				val word = queryWords[k]
				counter += document.getOrDefault(word, 0)
			}
			result[i][j] = counter
		}
	}
  return result
}

fun documentRelevance2(queries: List<String>, documents: List<Map<String, Int>>): List<List<Pair<Int, Int>>> {
	if (documents.isEmpty() || queries.isEmpty()) return emptyList()

	val result = MutableList<MutableList<Pair<Int, Int>>>(queries.size) { mutableListOf() }
	for (i in queries.indices) {
		for (j in documents.indices) {
			val document = documents[j]
			val queryWords = queries[i].split(" ")
			var counter = 0
			for (k in queryWords.indices) {
				val word = queryWords[k]
				counter += document.getOrDefault(word, 0)
			}
			result[i].add(j to counter)
		}
	}
	return result
}

fun countForSameWordsInLine(line: String): Map<String, Int> {
	val result = mutableMapOf<String, Int>()

	var i = 0
	var j = 0
	while (i <= line.lastIndex) {
		while (j != line.lastIndex + 1 && line[j] != ' ') j++

		val cur = line.substring(i, j)
		result.compute(cur) { _, quantity ->
			quantity?.let { it + 1 } ?: 1
		}
		j++
		i = j
	}

	return result
}

private data class Data(
	val serialNumberInDb: Int,
	val indexes: List<Int>
)