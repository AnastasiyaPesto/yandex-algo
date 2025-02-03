package ru.zentsova.yandex.sprint4.finalka

import java.io.BufferedReader

fun main() {
	val reader = System.`in`.bufferedReader()

	val searchIndex = mutableMapOf<String, MutableMap<Int, Int>>()
	repeat(reader.readInt()) { idx ->
		searchIndex(
			document = reader.readLine(),
			docSerialNum = idx + 1,
			searchIndex = searchIndex
		)
	}

	val inputQueries = (1..reader.readInt()).map { reader.readLine() }
	val queryDocumentRelevance = documentRelevance(queries = inputQueries, searchIndex = searchIndex)

  searchIndex.clear()

	val outputBuffer = StringBuilder()
	for (query in queryDocumentRelevance) {
		query.sort()
			.let { if (it.size < 5) it else it.subList(0, 5) }
			.forEach { outputBuffer.append(it.serialNumber).append(" ") }

		outputBuffer.append("\n")
	}
	println(outputBuffer)
}

fun searchIndex(document: String, docSerialNum: Int, searchIndex: MutableMap<String, MutableMap<Int, Int>>) {
	document.split(" ").forEach { word ->
		val wordsData = searchIndex.getOrPut(word) { mutableMapOf() }
		wordsData[docSerialNum] = wordsData.getOrDefault(docSerialNum, 0) + 1
	}
}

fun documentRelevance(queries: List<String>, searchIndex: Map<String, Map<Int, Int>>): List<List<DocumentData>> {
	if (searchIndex.isEmpty() || queries.isEmpty()) return emptyList()

	val result = mutableListOf<List<DocumentData>>()
	queries.forEach { query ->
		val queryDocumentData = mutableMapOf<Int, Int>()
		query.split(" ").toSet().forEach { word ->
			if (searchIndex.containsKey(word)) {
				searchIndex.getValue(word).forEach { (serialDocNum, count) ->
					queryDocumentData.compute(serialDocNum) { _, curCount -> curCount?.let { it + count } ?: count }
				}
			}
		}
		val documentDataList = queryDocumentData.map { DocumentData(it.key, it.value) }
		result.add(documentDataList)
	}

	return result
}

private fun BufferedReader.readInt() = readLine().toInt()

private fun List<DocumentData>.sort() = sortedWith(
	compareByDescending<DocumentData> { it.relevanceValue }.thenBy { it.serialNumber }
)

data class DocumentData(
	val serialNumber: Int,
	val relevanceValue: Int,
)