package ru.zentsova.yandex.sprint6

import java.util.*

//E. Компоненты связности
fun main() {
	val (vertexCount, edgeCount) = readGraphData()

	// список смежностей
	val adjacencyList = Array(vertexCount) { mutableListOf<Int>() }
	repeat(edgeCount) {
		val (from, to) = readInts()
		adjacencyList[from - 1].add(to - 1)
		adjacencyList[to - 1].add(from - 1)
	}

	val colors = MutableList(vertexCount) { 0 }
  var componentCount = 0

	for (i in adjacencyList.indices) {
		if (colors[i] == 0) {
			componentCount++
			val s = Stack<Int>()
			s.push(i)

			while (s.isNotEmpty()) {
				val v = s.pop()
				if (colors[v] == 0) {
					colors[v] = -1
					s.push(v)

					for (j in 0 until adjacencyList[v].size) {
						if (colors[adjacencyList[v][j]] == 0) {
							s.push(adjacencyList[v][j])
						}
					}
				} else {
					if (colors[v] == -1) {
						colors[v] = componentCount
					}
				}
			}
		}
	}

	println(componentCount)
	val result = Array(componentCount) { mutableListOf<Int>() }
	for (i in colors.indices) {
		result[colors[i] - 1].add(i + 1)
	}
	result.forEach { println(it.joinToString(" ")) }
}

private fun readInts() = readln().split(" ").map(String::toInt)
private fun readGraphData(): Pair<Int, Int> = readInts().let { it.first() to it.last() }