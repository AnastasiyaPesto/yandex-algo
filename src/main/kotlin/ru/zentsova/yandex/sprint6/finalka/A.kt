package ru.zentsova.yandex.sprint6.finalka

/*
-- Спринт 6. Финалка. A. Дорогая сеть --
Ссылка на удачную посылку: https://contest.yandex.ru/contest/25070/run-report/136609081/

-- ПРИНЦИП РАБОТЫ --
Алгоритм:
1. Считывание данных (читываю через BufferedReader):
Заполняю две структуры данных - adjacencyList и edgesWeight:

adjacencyList - списки смежностей, чтобы для каждой вершины получить соседние.
edgesWeight - матрица смежносте, где в ячейке лежит вес ребра. Это нужно для быстрого доступа к весу ребра.

Причем вес выбираю максимальный из двух, если между вершинами есть два ребра (т.к. граф ненаправленный, то так делать можно).

2. fun maxSpanningTree(adjacencyList, edgesWeight, vertexCount)
Начинаю с вершины 0. Помечаю ее как посещенную. Для этого заведен массив visited: Array<Boolean>.
Здесь же завожу счетчик (visitedCount), чтобы считать кол-во посещеных вершин, это поможет за константное время сделать вывод
о количестве компонент связности.
Все рёбра, исходящие из вершины 0 добавляю в бинарную кучу на максимум в формате пары - конечная вершина и вес.
Пользуюсь очередью из стандартной библиотеки - PriorityQueue.

Далее в цикле пока куча не пустая:
2.1. Достаю из неё ребро с максимальным весом.
2.2. Если конечная вершина (V), к которой ведёт ребро, ещё не посещена:
2.2.1. Помечаю ее как посещённую.
2.2.2. Прибавляю вес W к общему весу дерева (totalWeight).
2.2.3. Все рёбра из V к непосещённым вершинам добавляю в кучу.
2.3. Последним шагом сравниваю visitedCount и исходное число вершин.
Если граф имеет 1 компоненту связанности, то visitedCount равно vertexCount => вывожу значение  totalWeight,
иначе в графе более одной компоненты связности.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Алгоритм Прима на очереди с приоритетами.
Так как в алгоритме используется бинарная куча на максимум (по весам), то поиск последующего ребра
с максимальным весом происходит за O(log|V|) (V -  количество вершин)

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
O(∣E∣⋅log∣V∣), где ∣E∣ — количество рёбер в графе, а ∣V∣ — количество вершин.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(∣V∣) - массив для учета посещенных вершин (visited), где ∣V∣ — количество вершин в графе.
O(|E|) - приоритетная очеред, где ∣E∣ — количество рёбер в графе
O(|V|^2) - матрица смежностей (edgesWeight)
O(|V| + |E|) - список смежностей

Итог: O(|V|^2), где ∣V∣ — количество вершин в графе.
*/

import java.io.BufferedReader
import java.util.*

fun main() {
	val reader = System.`in`.bufferedReader()
	val (vertexCount, edgeCount) = reader.readVertexAndEdgeCount()

	val (edgesWeight, adjacencyList) = reader.readGraph(vertexCount, edgeCount)
	val maxWeight = maxSpanningTree(adjacencyList, edgesWeight, vertexCount)
	println(maxWeight ?: "Oops! I did it again")
}

fun maxSpanningTree(
	adjacencyList: Array<MutableList<Int>>,
	edgesWeight: Array<IntArray>,
	vertexCount: Int,
): Int? {
	if (vertexCount == 1) return 0
	if (edgesWeight.isEmpty()) return null

	var totalWeight = 0
	val visited = BooleanArray(vertexCount)
	val heap = PriorityQueue<Pair<Int, Int>>(compareByDescending { it.second })

	var visitedCount = 1
	visited[0] = true
	adjacencyList[0].forEach { neighbor ->
		val weight = edgesWeight[0][neighbor]
		heap.add(neighbor to weight)
	}

	while (heap.isNotEmpty()) {
		val (v, w) = heap.poll()
		if (!visited[v]) {
			visited[v] = true
			visitedCount++
			totalWeight += w

			adjacencyList[v].forEach { u ->
				if (!visited[u]) {
					val edgeWeight = edgesWeight[v][u]
					heap.add(u to edgeWeight)
				}
			}
		}
	}

	return if (visitedCount != vertexCount) null else totalWeight
}

private fun BufferedReader.readInts() = readLine().split(" ").map(String::toInt)

private fun BufferedReader.readVertexAndEdgeCount(): Pair<Int, Int> =
	readInts().let { it.first() to it.last() }

private fun BufferedReader.readGraph(
	vertexCount: Int,
	edgeCount: Int
): Pair<Array<IntArray>, Array<MutableList<Int>>> {
	val edgesWeight = Array(vertexCount) { IntArray(vertexCount) }
	val adjacencyList = Array(vertexCount) { mutableListOf<Int>() }

	repeat(edgeCount) {
		val (from, to, weight) = readInts().let { Triple(it[0] - 1, it[1] - 1, it[2]) }

		edgesWeight[from][to] = maxOf(edgesWeight[from][to], weight)
		edgesWeight[to][from] = maxOf(edgesWeight[to][from], weight)

		adjacencyList[from].add(to)
		adjacencyList[to].add(from)
	}

	return edgesWeight to adjacencyList
}
