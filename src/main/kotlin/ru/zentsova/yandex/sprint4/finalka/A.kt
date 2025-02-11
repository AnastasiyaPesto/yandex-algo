package ru.zentsova.yandex.sprint4.finalka

/*
-- Спринт 4. Финалка. B. Поисковая система --
Ссылка на удачную посылку: https://contest.yandex.ru/contest/24414/run-report/132727312/,
  после 1-го ревью: https://contest.yandex.ru/contest/24414/run-report/132958336/,
  после 2-го ревью: https://contest.yandex.ru/contest/24414/run-report/133035423/

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

	for (idx in 1..reader.readInt()) {
		searchIndex.addDocument(
			document = reader.readLine(),
			docSerialNum = idx,
		)
	}

	val topDocumentsCount = 5
	val comparator = compareByDescending<DocumentRelevanceData> { it.wordsQuantity }.thenBy { it.serialNumber }
	val outputBuffer = buildString {
		repeat(reader.readInt()) {
			val topDocuments = searchIndex
				.documentsRelevance(reader.readLine(), topDocumentsCount, comparator)
				.map { it.serialNumber }

			appendLine(topDocuments.joinToString(separator = " "))
		}
	}

	println(outputBuffer)
}

private fun BufferedReader.readInt() = readLine().toInt()

class SearchIndex {
	private val searchIndex: MutableMap<String, MutableList<DocumentRelevanceData>> = mutableMapOf()

	fun addDocument(document: String, docSerialNum: Int) = document
		.split(" ")
		.groupingBy { it }
		.eachCount()
		.forEach { (word, count) ->
			searchIndex
				.getOrPut(word) { mutableListOf() }
				.add(DocumentRelevanceData(serialNumber = docSerialNum, wordsQuantity = count))
		}

	fun documentsRelevance(query: String, countToReturn: Int, comparator: Comparator<DocumentRelevanceData>): List<DocumentRelevanceData> {
		if (query.isBlank()) return emptyList()

		val result = mutableListOf<DocumentRelevanceData>()
		query
			.split(" ")
			.toSet()
			.flatMap { word -> searchIndex[word].orEmpty() }
			.groupBy({ it.serialNumber }, { it.wordsQuantity })
			.forEach { (serialNumber, counts) ->
				val document = DocumentRelevanceData(serialNumber = serialNumber, wordsQuantity = counts.sum())
				when {
					result.size < countToReturn -> {
						result.add(document)
						result.sortWith(comparator)
					}
					comparator.compare(document, result.last()) < 0 -> {
						result[result.lastIndex] = document
						result.sortWith(comparator)
					}
				}
			}

		return result
	}
}

data class DocumentRelevanceData(
	val wordsQuantity: Int,
	val serialNumber: Int
)
