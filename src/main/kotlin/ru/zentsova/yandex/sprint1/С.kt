package ru.zentsova.yandex.sprint1

data class Matrix(val n: Int, val m: Int, val data: List<IntArray>)

fun main() {
	val matrix = readMatrix()
	val x = readln().toInt()
	val y = readln().toInt()
	val neighbours = getNeighbours(matrix, x, y).sorted()
	neighbours.forEach { print("$it ") }
}

private fun getNeighbours(matrix: Matrix, x: Int, y: Int): List<Int> {
	return buildList {
		if (x - 1 >= 0) add(matrix.data[x - 1][y])
		if (x + 1 < matrix.n) add(matrix.data[x + 1][y])
		if (y - 1 >= 0) add(matrix.data[x][y - 1])
		if (y + 1 < matrix.m) add(matrix.data[x][y + 1])
	}
}

fun readMatrix(): Matrix {
	val n = readInt()
	val m = readInt()
	val matrix = buildList(n) {
		for (i in 0 until n) {
			add(readInts().toIntArray())
		}
	}
	return Matrix(n, m, matrix)
}


private fun readStr() = readln()
private fun readInt() = readStr().toInt()
private fun readStrings() = readStr().split(" ")
private fun readInts() = readStrings().map { it.toInt() } // list of ints