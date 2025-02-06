package ru.zentsova.yandex.sprint4.finalka

/*
-- Спринт 4. Финалка. B. Поисковая система --
Ссылка на удачную посылку: https://contest.yandex.ru/contest/24414/run-report/132727312/,
  после 1-го ревью: https://contest.yandex.ru/contest/24414/run-report/132958336/

-- ПРИНЦИП РАБОТЫ --

При считывании документов сразу строится поисковый индекс: Map<String, List<DocumentData>>,
где ключ это уникальное слово из всех документов, значение - список объектов:
- DocumentData.serialNumber - порядковый номер документа
- DocumentData.wordQuantity - сколько раз встречается слово (key) в предложении

Как вычисляется value:
Каждый документ заранее известен, поэтому с помощью группировки подсчитываю кол-во повторений каждого слова в документе,
и кладу это значение в поисковый индекс

Как вычисляется релевантность для каждого запроса:
1. Для каждого уникального слова из поискового индекса получаю листы - в каких документах и сколько раз встречается слово
2. С помощью flatMap преобразую в список
3. В цикле заполняю вспомогательный массив до 5 элементов и сортирую
Если элементов больше, чем 5, то сравниваю текущее значение с последним значением из вспомогательного массива.
Если текущий элемент больше, чем последний, то перезаписываю последний элемент и произвожу сортировку.
Так как массив всего из 5 элементов (константа), то сортировка происходит за линию + для хранения понадобится O(1)

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
O(1) - сортировка документов для одного запроса (сортировка 5 элементов)

Итоговая:
O(ND) + O(MK) + O(MK) + O(MNLogN) = O(ND) + O(MK)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --

O(M) - хранение поискового индекса, где M - количество слов во всех документах.

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
				} else if (compare(document, result.last())) {
					result[result.lastIndex] = document
					result.sortWith(comparator)
				}
			}

		return result
	}

	private fun compare(o1: DocumentRelevanceData, o2: DocumentRelevanceData): Boolean =
		o1.relevance > o2.relevance || o1.relevance == o2.relevance && o1.serialNumber < o2.serialNumber
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