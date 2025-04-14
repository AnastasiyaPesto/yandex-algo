package ru.zentsova.yandex.sprint6.finalka

/*
-- Спринт 6. Финалка. B. Водный Мир --
Ссылка на удачную посылку: https://contest.yandex.ru/contest/25070/run-report/136637340/

-- ПРИНЦИП РАБОТЫ --
1. fun mainDFS()
Двумерный массив visited для отслеживания посещенных клеток
Запускаем двойной цикл:
- Если клетка — # и мы её ещё не посещали — это новый остров
- Увеличиваем счётчик островов
- Запускаем dfs(...), чтобы пройти по этому острову
- Запоминаем его размер, если он больше предыдущего максимального

2. fun dfs()
dx и dy - вспомогательные массивы, которые описывают движение в четырёх направлениях: вверх, вправо, вниз, влево
- Пока есть что обходить — достаём вершину из стека
- Обходим всех четырёх соседей (с проверкой границ и что они — земля и не посещены)
- Помечаем как посещённые и добавляем в стек
- Увеличиваем size — размер текущего острова
- Возвращаем пару: количество островов и размер самого большого

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Алгоритм перебирает каждую клетку, значит не пропусти ничего.
Ищем связные компоненты через DFS. И проверяем границы.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
O(n * m) — где n * m - размер карты (каждая клетка обрабатывается максимум один раз)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(n * m) — где n * m - размер карты (хранение карты и массива visited)
*/

import java.io.BufferedReader

fun main() {
	val reader = System.`in`.bufferedReader()
	val (n, m) = reader.readInts()
	val map = Array(n) { reader.readChars() }

	val (islandCount, maxSize) = mainDFS(map, n, m)

	println("$islandCount $maxSize")
}

fun mainDFS(map: Array<CharArray>, n: Int, m: Int): Pair<Int, Int> {
	val visited = Array(n) { BooleanArray(m) }

	var islandCount = 0
	var maxSize = 0

	for (i in 0 until n) {
		for (j in 0 until m) {
			if (map[i][j] == '#' && !visited[i][j]) {
				islandCount++
				val size = dfs(map, i, j, n, m, visited)
				if (size > maxSize) maxSize = size
			}
		}
	}

	return islandCount to maxSize
}

fun dfs(map: Array<CharArray>, x: Int, y: Int, n: Int, m: Int, visited: Array<BooleanArray>): Int {
	val dx = listOf(-1, 0, 1, 0)
	val dy = listOf(0, 1, 0, -1)

	val stack = ArrayDeque<Pair<Int, Int>>()
	stack.add(x to y)
	visited[x][y] = true
	var size = 1

	while (stack.isNotEmpty()) {
		val (cx, cy) = stack.removeLast()
		for (dir in 0 until 4) {
			val nx = cx + dx[dir]
			val ny = cy + dy[dir]
			if (nx in 0 until n && ny in 0 until m && !visited[nx][ny] && map[nx][ny] == '#') {
				visited[nx][ny] = true
				stack.add(nx to ny)
				size++
			}
		}
	}

	return size
}

private fun BufferedReader.readInts() = readLine().split(" ").map(String::toInt)
private fun BufferedReader.readChars() = readLine().toCharArray()
