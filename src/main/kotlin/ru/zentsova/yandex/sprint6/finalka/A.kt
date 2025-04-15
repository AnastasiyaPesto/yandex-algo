package ru.zentsova.yandex.sprint6.finalka

/*
-- Спринт 6. Финалка. A. Дорогая сеть --
Ссылка на удачную посылку: https://contest.yandex.ru/contest/25070/run-report/136811848/

-- ПРИНЦИП РАБОТЫ --
Алгоритм:
1. Считывание данных (читываю через BufferedReader):
Заполняю структуру данных - graph: Array<List<Edge>>:

Список ребер (ребро в формате - конечная веришна + вес ребра).
Начало ребра это первый индекс массива.

2. fun maxSpanningTree(graph)
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

Тут мне было не супер понятно, но смею предоложить, что зависимость от вершин (несмотря на то, что в куче хранятся ребра),
потому что каждый раз мы ищем ребра, которые исходят из каждой вершины.
Обрабатываем вершину один раз, а потом добавляем все ребра, исходящие из нее и работаем с ними.

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

	val graph = reader.readGraph(vertexCount, edgeCount)
	val maxWeight = maxSpanningTree(graph)
	println(maxWeight ?: "Oops! I did it again")
}

fun maxSpanningTree(graph: Array<MutableList<Edge>>): Int? {
	var totalWeight = 0
	val visited = BooleanArray(graph.size)
	val heap = PriorityQueue<Edge>(compareByDescending { it.weight })
	var visitedCount = 0

	fun visitVertex(v: Int) {
		visited[v] = true
		visitedCount++
		graph[v].forEach { edge ->
			if (!visited[edge.to]) {
				heap.add(edge)
			}
		}
	}

	visitVertex(0)
	while (heap.isNotEmpty()) {
		val edge = heap.poll()
		if (!visited[edge.to]) {
			totalWeight += edge.weight
			visitVertex(edge.to)
		}
	}

	return if (visitedCount != graph.size) null else totalWeight
}

private fun BufferedReader.readInts() = readLine().split(" ").map(String::toInt)

private fun BufferedReader.readVertexAndEdgeCount(): Pair<Int, Int> =
	readInts().let { it.first() to it.last() }

private fun BufferedReader.readGraph(vertexCount: Int, edgeCount: Int): Array<MutableList<Edge>> {
	val graph = Array(vertexCount) { mutableListOf<Edge>() }

	repeat(edgeCount) {
		val (from, to, weight) = readInts()

		graph[from - 1].add(Edge(to - 1, weight))
		graph[to - 1].add(Edge(from - 1, weight))
	}

	return graph
}

data class Edge(val to: Int, val weight: Int)