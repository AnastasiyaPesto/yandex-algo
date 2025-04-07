package ru.zentsova.yandex.sprint6

import java.util.*

// J. Топологическая сортировка
fun main() {
	val (vertexCount, edgeCount) = readGraphData()
	// список смежностей
	val adjacencyList = Array(vertexCount) { mutableListOf<Int>() }
	repeat(edgeCount) {
		val (from, to) = readInts()
		adjacencyList[from - 1].add(to - 1)
	}

	val sorted = topologicalSort(adjacencyList)
	print(sorted.reversed().joinToString(separator = " ") { "${it + 1}"})
}

private fun topologicalSort(adjacencyList:  Array<MutableList<Int>>): Stack<Int> {
	val result = Stack<Int>()
	val colors = Array(adjacencyList.size) { -1 }

	for (i in adjacencyList.indices) {
		if (colors[i] == -1) {
			val s = Stack<Int>()
			s.push(i)

			while (s.isNotEmpty()) {
				val v = s.pop()
				if (colors[v] == -1) {
					colors[v] = 0
					s.push(v)

					adjacencyList[v].sortDescending()

					for (j in 0 until adjacencyList[v].size) {
						if (colors[adjacencyList[v][j]] == -1) {
							s.push(adjacencyList[v][j])
						}
					}
				} else {
					if (colors[v] == 0) {
						result.push(v)
						colors[v] = 1
					}
				}
			}
		}
	}

	return result
}

private fun readInts() = readln().split(" ").map(String::toInt)
private fun readGraphData(): Pair<Int, Int> = readInts().let { it.first() to it.last() }