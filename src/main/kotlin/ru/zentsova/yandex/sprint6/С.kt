package ru.zentsova.yandex.sprint6

import java.util.*

// C. DFS

fun main() {
	val (vertexCount, edgeCount) = readGraphData()

	val adjacencyList = Array(vertexCount) { mutableListOf<Int>() }
	repeat(edgeCount) {
		val (from, to) = readInts()
		adjacencyList[from - 1].add(to - 1)
		adjacencyList[to - 1].add(from - 1)
	}

	val startVertex = readInt()
	val dfsResult = dfs(adjacencyList, startVertex)
	print(dfsResult.joinToString(" "))
}

private fun dfs(adjacencyList: Array<MutableList<Int>>, startVertex: Int): List<Int> {
	val result = mutableListOf<Int>()
	val visited = BooleanArray(adjacencyList.size)
	val stack: Stack<Int> = Stack()

	adjacencyList.forEach { it.sortDescending() }

	stack.push(startVertex - 1)
	while (stack.isNotEmpty()) {
		val v = stack.pop()
		if (visited[v]) continue

		visited[v] = true
		result.add(v + 1)

		adjacencyList[v].forEach { neighbor ->
			if (!visited[neighbor]) stack.push(neighbor)
		}
	}
	return result
}

private fun readInt() = readln().toInt()
private fun readInts() = readln().split(" ").map(String::toInt)
private fun readGraphData(): Pair<Int, Int> = readInts().let { it.first() to it.last() }
