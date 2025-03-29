package ru.zentsova.yandex.sprint6

import ru.zentsova.yandex.sprint6.Color.*
import java.util.*

// C. DFS

fun main() {
	val (vertexCount, edgeCount) = readGraphData()

	val vertexColor = Array(vertexCount) { WHITE }

	val adjacencyMatrix = Array(vertexCount) { arrayOf<Int>() }
	for (i in 0 until edgeCount) {
		adjacencyMatrix[i] = Array(i + 1) { 0 }
	}

	for (i in 0 until edgeCount) {
		val (from, to) = readInts()
		val (v1, v2) = maxOf(from, to) to minOf(from, to)
		adjacencyMatrix[v1 - 1][v2 - 1] = 1
	}
	val startVertex = readInt()
	val dfsResult = dfs(adjacencyMatrix, startVertex, vertexColor)
	print(dfsResult.joinToString(" "))
}

private fun dfs(adjacencyMatrix: Array<Array<Int>>, startVertex: Int, vertexColor: Array<Color>): List<Int> {
	val result = mutableListOf<Int>()
	val stack: Stack<Int> = Stack()
	stack.push(startVertex - 1)
	while (stack.isNotEmpty()) {
		val v = stack.pop()
		if (vertexColor[v] == WHITE) {
			vertexColor[v] = GREY
			result.add(v + 1)
			stack.push(v)
			val adjacentVertices = adjacencyMatrix[v]
			adjacentVertices.forEachIndexed { vertex, value ->
				if (value != 0 && vertexColor[vertex] == WHITE) {
					stack.push(vertex)
				}
			}
		} else if (vertexColor[v] == GREY) {
			vertexColor[v] = BLACK
		}
	}
	return result
}

private fun readInt() = readln().toInt()
private fun readInts() = readln().split(" ").map(String::toInt)
private fun readGraphData(): Pair<Int, Int> = readInts().let { it.first() to it.last() }

private enum class Color {
	WHITE,
	GREY,
	BLACK
}