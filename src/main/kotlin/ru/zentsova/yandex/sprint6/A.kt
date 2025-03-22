package ru.zentsova.yandex.sprint6

fun main() {
  val (vertexCount, edgeCount) = readGraphData()
	val adjacencyList = mutableMapOf<Int, MutableList<Int>>()

	repeat(edgeCount) {
		val (from, to) = readInts()
		adjacencyList.getOrPut(from) { mutableListOf() }.add(to)
	}

	for (v in 1..vertexCount) {
		val list = adjacencyList.getOrDefault(v, emptyList())
		println("${list.size} ${list.joinToString(" ")}".trim())
	}
}

private fun readInts() = readln().split(" ").map(String::toInt)
private fun readGraphData(): Pair<Int, Int> = readInts().let { it[0] to it[1] }