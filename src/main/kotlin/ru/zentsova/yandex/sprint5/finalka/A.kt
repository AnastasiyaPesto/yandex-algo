package ru.zentsova.yandex.sprint5.finalka

/*
-- Спринт 5. Финалка. A. Пирамидальная сортировка --
Ссылка на удачную посылку:

-- ПРИНЦИП РАБОТЫ --

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --

*/

import java.io.BufferedReader

fun main() {
	val reader = System.`in`.bufferedReader()
	val internsCount = reader.readInt()
	val internsHeap = Heap<Intern>(internsCount)
	repeat(internsCount) {
		internsHeap.add(reader.intern)
	}
}

private fun BufferedReader.readInt() = readLine().toInt()

private val BufferedReader.intern: Intern
	get() {
		val (login, tasksCount, penaltiesCount) = readLine().split(" ")
		return Intern(
			login = login,
			taskCount = tasksCount.toInt(),
			penaltyCount = penaltiesCount.toInt()
		)
	}

class Heap<T : Any>(val size: Int) {
	val heap: MutableList<T?> = MutableList(size) { null }

	fun add(elem: T) {

	}
}

data class Intern(
	val login: String,
	val taskCount: Int,
	val penaltyCount: Int,
)