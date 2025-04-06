package ru.zentsova.yandex.sprint6

import java.util.*

// J. Топологическая сортировка
fun main() {
	val (vertexCount, edgeCount) = readGraphData()
	val adjacencyList = Array(vertexCount) { mutableListOf<Int>() }
	repeat(edgeCount) {
		val (from, to) = readInts()
		adjacencyList[from - 1].add(to - 1)
	}

	val sorted = Stack<Int>()
	val color = Array(vertexCount) { "white" }
	for (i in 0 until vertexCount) {
		sorted.addAll(topologicalSort(adjacencyList, i, color))
	}

	print(sorted.joinToString(separator = " ") { "${it + 1}"})
}

private fun topologicalSort(adjacencyList: Array<MutableList<Int>>, v: Int, color: Array<String>): Stack<Int> {
	val result = Stack<Int>()

	color[v] = "gray"
	for (w in adjacencyList[v]) {
		if (color[w] == "white") {
			topologicalSort(adjacencyList, w, color)
		}
	}
	color[v] = "black"
	result.push(v)

	return result
}

private fun readInts() = readln().split(" ").map(String::toInt)
private fun readGraphData(): Pair<Int, Int> = readInts().let { it.first() to it.last() }