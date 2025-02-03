package ru.zentsova.yandex.sprint4.finalka

/*
-- Спринт 4. Финалка. B. Поисковая система --
Ссылка на удачную посылку: https://contest.yandex.ru/contest/24414/run-report/132727312/

-- ПРИНЦИП РАБОТЫ --

При считывании документов сразу строится поисковый индекс: Map<String, Map<Int, Int>>,
где ключ это слово из документа, значение - словарь:
порядковый номер документа <-> сколько раз слово встречается в этом документе.

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

	val searchIndex = mutableMapOf<String, MutableMap<Int, Int>>()
	repeat(reader.readInt()) { idx ->
		searchIndex(
			document = reader.readLine(),
			docSerialNum = idx + 1,
			searchIndex = searchIndex
		)
	}

	val outputBuffer = StringBuilder()
	repeat(reader.readInt()) {
		val query = reader.readLine()
		val documentsRelevance = documentRelevance(query = query, searchIndex = searchIndex)
		documentsRelevance.sort()
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

fun documentRelevance(query: String, searchIndex: Map<String, Map<Int, Int>>): List<DocumentData> {
	if (searchIndex.isEmpty() || query.isBlank()) return emptyList()

	val queryDocumentData = mutableMapOf<Int, Int>()
	query.split(" ").toSet().forEach { word ->
		if (searchIndex.containsKey(word)) {
			searchIndex.getValue(word).forEach { (serialDocNum, count) ->
				queryDocumentData.compute(serialDocNum) { _, curCount -> curCount?.let { it + count } ?: count }
			}
		}
	}

	return queryDocumentData.map { DocumentData(it.key, it.value) }
}

private fun BufferedReader.readInt() = readLine().toInt()

private fun List<DocumentData>.sort() = sortedWith(
	compareByDescending<DocumentData> { it.relevanceValue }.thenBy { it.serialNumber }
)

data class DocumentData(
	val serialNumber: Int,
	val relevanceValue: Int,
)