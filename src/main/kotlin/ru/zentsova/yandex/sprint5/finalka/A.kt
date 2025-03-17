package ru.zentsova.yandex.sprint5.finalka

/*
-- Спринт 5. Финалка. A. Пирамидальная сортировка --
Ссылка на удачную посылку: https://contest.yandex.ru/contest/24810/run-report/134973138/

-- ПРИНЦИП РАБОТЫ --
Пирамидальная сортировка осуществляется с использованием бинарной кучи (пирамиды).
Пирамида (binary heap) — это структура данных, представляющая собой объект-массив, который можно рассматривать как почти полное бинарное дерево.
По факту это массив, где нет пробелов между елементами (кроме элемента с индексом 0). По этому индексу в моём варианте хранится null.
Так же соблюдаются условия A[parent(i)] >= A[i] для max-heap и A[parent(i)] <= A[i] для min-heap.

Краткое описание алгоритма:
1. Из входящих данных формируем бинарную кучу (max-heap). Каждый новый элемент добавляется в конец и "просеивается вверх".
2. Когда куча полностью сформирована, начинаем по одному извлекать элемент по индексу с номером 1.
3. Так как по индексу 1 теперь пустое значение, то его нужно заполнить. Берем крайний правый элемент и помещаем на вершины (индекс 1).
Этот элемент "просеиваем вниз".

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
В бинарной куче на максимум на вершине (индекс 1) всегда лежит наибольший элемент в текущий момент времени.
Поэтому, извлекая на каждом шаге элемент из вершины, можно быть уверенными, что каждый последующий элемент будет не больше, чем предыдущий.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
O(1) - создание бинарной кучи
O(NlogN) - вставка элемента в бинарную кучу (вставка в конец + просеивание вверх)
O(NlogN) - извлечение элемента из вершины (извлечение + просеивание вниз элемента, который пришел на замену извлеченного)
O(1) + O(NlogN) + O(NlogN) = O(NlogN)

n - кол-во элементов

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(n) - хранение бинарной кучи (массив)

n - кол-во элементов
*/

import java.io.BufferedReader

fun comparator() = compareByDescending<Intern> { it.taskCount }.thenBy { it.penaltyCount }.thenBy { it.login }

fun main() {
	val reader = System.`in`.bufferedReader()
	val internsCount = reader.readInt()
	val interns = reader.readInterns(internsCount)
	heapSort(interns, comparator())
	interns.forEach { println(it.login) }
}

private fun BufferedReader.readInt() = readLine().toInt()

private fun BufferedReader.readInterns(n: Int) = Array(n) {
	val (login, tasksCount, penaltiesCount) = readLine().split(" ")
	Intern(
		login = login,
		taskCount = tasksCount.toInt(),
		penaltyCount = penaltiesCount.toInt()
	)
}

data class Intern(
	val login: String,
	val taskCount: Int,
	val penaltyCount: Int,
)

fun <T> heapSort(array: Array<T>, comparator: Comparator<T>) {
	val n = array.size

	for (i in (n / 2 - 1) downTo 0) {
		siftDown(array, i, n, comparator)
	}

	for (i in n - 1 downTo 1) {
		array.swap(0, i)
		siftDown(array, 0, i, comparator)
	}
}

fun <T> siftDown(array: Array<T>, idx: Int, heapSize: Int, comparator: Comparator<T>) {
	var largest = idx
	val left = 2 * idx + 1
	val right = 2 * idx + 2

	if (left < heapSize && comparator.compare(array[left], array[largest]) > 0) {
		largest = left
	}
	if (right < heapSize && comparator.compare(array[right], array[largest]) > 0) {
		largest = right
	}

	if (largest != idx) {
		array.swap(idx, largest)
		siftDown(array, largest, heapSize, comparator)
	}
}
//
//	fun <T> siftDown(array: Array<T>, idx: Int, comparator: Comparator<in T>) {
//		val left = 2 * idx + 1
//		val right = 2 * idx + 2
//
//		if (left >= array.size) return
//
//		val largestIdx = if (right < array.size && comparator.compare(array[left], array[right]) > 0) right else left
//
//		if (comparator.compare(array[idx], array[largestIdx]) > 0) {
//			array.swap(idx, largestIdx)
//			siftDown(array, largestIdx, comparator)
//		}
//	}

private fun <T> Array<T>.swap(i: Int, j: Int) {
	val temp = this[i]
	this[i] = this[j]
	this[j] = temp
}