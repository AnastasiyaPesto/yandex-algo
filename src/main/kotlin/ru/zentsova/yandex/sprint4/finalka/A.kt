package ru.zentsova.yandex.sprint4.finalka

/*
-- Спринт 4. Финалка. B. Поисковая система --
Ссылка на удачную посылку: https://contest.yandex.ru/contest/24414/run-report/132727312/

-- ПРИНЦИП РАБОТЫ --

При считывании документов сразу строится поисковый индекс: Map<String, List<DocumentData>>,
где ключ это уникальное слово из всех документов, значение - список объектов:
- DocumentData.serialNumber - порядковый номер документа
- DocumentData.wordQuantity - сколько раз встречается слово (key) в предложении


// todo

Для каждого запрос in-place вычисляется релевантность всех документов,
полученный массив сортируется и выводится рейтинг из первых 5-ти документов.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --

Так как поисковый индекс это словарь, то доступ по ключу в среднем O(1),
поэтому можно довольно быстро вычислить для запроса релевантность каждого документа.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --

O(ND) - построение поискового индекса,
        где N - количество документов, D количество слов в каждом документе.
O(K) - из набора слов исходного запроса построить коллекцию из уникальных слов,
       где K - количество слов в исходном запросе.
O(K) - вычисление для одного запроса релевантность по каждому документ,
       где K - количество слов в запросе.
O(NLogN) - сортировка документов для одного запроса.

Итоговая:
O(ND) + O(MK) + O(MK) + O(MNLogN) = O(ND) + O(MK) + O(MNLogN)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --

O(M) - хранение поискового индекса, где M - количество слов во всех документах.
O(1) - хранение словаря (значение в мапе поискового индекса)

Итоговая: O(M)
*/

import java.io.BufferedReader

fun main() {
	val reader = System.`in`.bufferedReader()

	val searchIndex = SearchIndex()

	repeat(reader.readInt()) { idx ->
		searchIndex.addDocument(
			document = reader.readLine(),
			docSerialNum = idx + 1,
		)
	}

	val topDocumentsCount = 5
	val comparator = 	compareByDescending<DocumentRelevanceData> { it.relevance }.thenBy { it.serialNumber }
	val outputBuffer = buildString {
		repeat(reader.readInt()) {
			searchIndex
				.documentsRelevance(reader.readLine(), topDocumentsCount, comparator)
				.forEach { append(it.serialNumber).append(" ") }

			append("\n")
		}
	}

	println(outputBuffer)
}

private fun BufferedReader.readInt() = readLine().toInt()

class SearchIndex {
	private val searchIndex: MutableMap<String, MutableList<DocumentData>> = mutableMapOf()

	fun addDocument(document: String, docSerialNum: Int) {
		val words = document.split(" ")
		val countByWord = words.groupingBy { it }.eachCount()

		words.toSet().forEach { word ->
			searchIndex.compute(word) { _, documentData ->
				(documentData ?: mutableListOf()).apply {
					add(
						DocumentData(
							serialNumber = docSerialNum,
							wordQuantity = countByWord.getValue(word)
						)
					)
				}
			}
		}
	}

	fun documentsRelevance(query: String, countToReturn: Int, comparator: Comparator<DocumentRelevanceData>, ): List<DocumentRelevanceData> {
		if (query.isBlank()) return emptyList()

		val result = mutableListOf<DocumentRelevanceData>()
		query.split(" ").toSet()
			.flatMap { word -> searchIndex[word].orEmpty() }
			.groupBy({ it.serialNumber }, { it.wordQuantity })
			.forEach { (serialNumber, counts) ->
				val document = DocumentRelevanceData(serialNumber = serialNumber, relevance = counts.sum())
				if (result.size < countToReturn) {
					result.add(document)
					result.sortWith(comparator)
				} else if (document.relevance > result.last().relevance ||
					         document.relevance == result.last().relevance && document.serialNumber < result.last().serialNumber)
				{
					result[result.lastIndex] = document
					result.sortWith(comparator)
				}
			}

		return result
	}
}

interface Document {
	val serialNumber: Int
}

data class DocumentRelevanceData(
	val relevance: Int,
	override val serialNumber: Int
) : Document

data class DocumentData(
	val wordQuantity: Int,
	override val serialNumber: Int
) : Document