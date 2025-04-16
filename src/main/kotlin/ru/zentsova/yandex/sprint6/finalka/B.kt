package ru.zentsova.yandex.sprint6.finalka

/*
-- Спринт 6. Финалка. B. Водный Мир --
Ссылка на удачную посылку: https://contest.yandex.ru/contest/25070/run-report/136858469/

-- ПРИНЦИП РАБОТЫ --
1. fun mainDFS()
Заведем массив один массив map: Array<BooleanArray>
Запускаем двойной цикл:
- Если клетка по индексу [x, y] = true, то нужно обработать данную клетку и пометить как false.
- Увеличиваем счётчик островов
- Запускаем dfs(...), чтобы пройти по этому острову
- Запоминаем его размер, если он больше предыдущего максимального

2. fun dfs()
- Завела массив в направлениями directions и сложив текущую точку со всем значениями - получаю всех соседей
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
  val map = Array(n) { reader.readInputData(m) }

  val (islandCount, maxSize) = mainDFS(map)

  println("$islandCount $maxSize")
}

fun mainDFS(map: Array<BooleanArray>): Pair<Int, Int> {
  val n = map.size
  val m = map.first().size

  var islandCount = 0
  var maxSize = 0

  for (x in 0 until n) {
    for (y in 0 until m) {
      val point = Point(x, y)
      if (map[point]) {
        val size = dfs(map, point)
        islandCount++
        maxSize = maxOf(maxSize, size)
      }
    }
  }

  return islandCount to maxSize
}

fun dfs(map: Array<BooleanArray>, start: Point): Int {
  val stack = ArrayDeque<Point>()
  stack.add(start)
  map[start] = false
  var size = 1

  while (stack.isNotEmpty()) {
    val current = stack.removeLast()
    for (neighbor in current.neighbors()) {
      if (map[neighbor]) {
        map[neighbor] = false
        stack.add(neighbor)
        size++
      }
    }
  }

  return size
}

private fun BufferedReader.readInts() = readLine().split(" ").map(String::toInt)
private fun BufferedReader.readInputData(size: Int): BooleanArray {
  val line = readLine()
  return BooleanArray(size) { line[it] == '#' }
}

data class Point(val x: Int, val y: Int) {
  operator fun plus(other: Point) = Point(x + other.x, y + other.y)
  fun neighbors(): List<Point> = directions.map { this + it }

  companion object {
    private val directions = listOf(
      Point(-1, 0), Point(1, 0),
      Point(0, -1), Point(0, 1)
    )
  }
}

operator fun Array<BooleanArray>.get(p: Point): Boolean {
  val inBounds = p.x in indices && p.y in this[0].indices
  return if (inBounds) this[p.x][p.y] else false
}

operator fun Array<BooleanArray>.set(p: Point, value: Boolean) {
  this[p.x][p.y] = value
}
