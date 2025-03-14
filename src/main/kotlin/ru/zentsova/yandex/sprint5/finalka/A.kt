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
	val internsMaxHeap = Heap<Intern>()
	repeat(internsCount) {
		internsMaxHeap.add(reader.intern, comparator())
	}
	val sortedInterns = Array(internsCount) {
		internsMaxHeap.pop(comparator())
	}
	print(sortedInterns.joinToString(separator = "\n") { it!!.login })
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

private fun comparator() =
	compareByDescending<Intern> { it.taskCount }.thenBy { it.penaltyCount }.thenBy { it.login }

class Heap<T : Any> {
	private val firstIndex = 1
	private var capacity = 0
	val heap: MutableList<T?> = mutableListOf()

	fun add(elem: T, comparator: Comparator<in T>) {
		if (heap.isEmpty()) heap.add(null)
		heap.add(elem)
		capacity++
		siftUp(heap.lastIndex, comparator)
	}

	fun pop(comparator: Comparator<in T>): T? {
		val max = if (notEmpty()) {
			val maxElem = heap[firstIndex]
			heap[firstIndex] = null
			capacity--
			maxElem
		} else null

		if (notEmpty()) {
			val lastElemIdx = heap.indexOfLast { it !== null }
			heap[firstIndex] = heap[lastElemIdx]
			heap[lastElemIdx] = null
			siftDown(firstIndex, comparator)
		}

		return max
	}

	private fun notEmpty(): Boolean = (heap.size > 1 && capacity != 0)

	private fun siftUp(idx: Int, comparator: Comparator<in T>) {
		if (idx == 1 || comparator.compare(heap[idx / 2], heap[idx]) >= 0) return

		heap.swap(idx, idx / 2)
		siftUp(idx / 2, comparator)
	}

	private fun siftDown(idx: Int, comparator: Comparator<in T>) {
		val left = 2 * idx
		val right = left + 1

		if (left >= heap.size) return

		val largestIdx = if (right < heap.size && comparator.compare(heap[right], heap[left]) > 0) right else left

		if (comparator.compare(heap[idx], heap[largestIdx]) < 0) {
			heap.swap(idx, largestIdx)
			siftDown(largestIdx, comparator)
		}
	}

	private fun MutableList<T?>.swap(from: Int, to: Int) {
		val tmp = this[from]
		this[from] = this[to]
		this[to] = tmp
	}
}

data class Intern(
	val login: String,
	val taskCount: Int,
	val penaltyCount: Int,
)