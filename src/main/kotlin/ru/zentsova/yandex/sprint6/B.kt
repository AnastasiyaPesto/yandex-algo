package ru.zentsova.yandex.sprint6

fun main() {
	val (vertexCount, edgeCount) = readGraphData()
	val adjacencyMatrix = Array(vertexCount) { IntArray(vertexCount) }

	repeat(edgeCount) {
		val (from, to) = readInts()
		adjacencyMatrix[from - 1][to - 1] = 1
	}

	adjacencyMatrix.forEach { println(it.joinToString(" ")) }
}

private fun readInts() = readln().split(" ").map(String::toInt)
private fun readGraphData(): Pair<Int, Int> = readInts().let { it[0] to it[1] }
