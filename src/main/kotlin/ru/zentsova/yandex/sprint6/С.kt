package ru.zentsova.yandex.sprint6

// C. DFS

fun main() {
	val (vertexCount, edgeCount) = readGraphData()

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

	println(startVertex)
}

private fun readInt() = readln().toInt()
private fun readInts() = readln().split(" ").map(String::toInt)
private fun readGraphData(): Pair<Int, Int> = readInts().let { it.first() to it.last() }