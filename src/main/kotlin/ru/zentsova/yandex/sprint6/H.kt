package ru.zentsova.yandex.sprint6

var time = 0

// H. Время выходить
fun main() {
  val (vertexCount, edgeCount) = readInts()

	val graph = Array(vertexCount) { mutableListOf<Int>() }
	repeat(edgeCount) {
		val (from, to) = readInts()
		graph[from - 1].add(to - 1)
	}
	graph.forEach { it.sort() }
	val times = Array(vertexCount) { Time() }
	val used = BooleanArray(vertexCount)
	dfs(0, graph, used, times)
	times.forEach { println("${it.into} ${it.out}") }
}

fun dfs(
	from: Int,
	graph: Array<MutableList<Int>>,
	used: BooleanArray,
	times: Array<Time>
) {
  times[from].into = time
	time++

	used[from] = true
	for (to in graph[from]) {
		if (!used[to]) {
			dfs(to, graph, used, times)
		}
	}

	times[from].out = time
	time++
}

private fun readInts() = readln().split(" ").map(String::toInt)

data class Time(
	var into: Int = 0,
	var out: Int = 0,
)