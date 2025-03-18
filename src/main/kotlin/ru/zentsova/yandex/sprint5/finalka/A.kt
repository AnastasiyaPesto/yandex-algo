package ru.zentsova.yandex.sprint5.finalka

/*
-- Спринт 5. Финалка. A. Пирамидальная сортировка --
Ссылка на удачную посылку: https://contest.yandex.ru/contest/24810/run-report/134973138/
Ссылка после исправлений 1-го ревью: https://contest.yandex.ru/contest/24810/run-report/135072515/

-- ПРИНЦИП РАБОТЫ --
Пирамидальная сортировка осуществляется с использованием бинарной кучи (пирамиды).
Пирамида (binary heap) — это структура данных, представляющая собой объект-массив, который можно рассматривать как почти полное бинарное дерево.
По факту это массив, где нет пробелов между элементами (кроме элемента с индексом 0). По этому индексу в моём варианте хранится null.
Так же соблюдаются условия A[parent(i)] >= A[i] для max-heap и A[parent(i)] <= A[i] для min-heap.

Краткое описание алгоритма:
1. Метод heapSort() принимает неупорядоченный массив и компаратор.
2. Выполняется построение кучи за линейное время.
   Перестановка нужна только для вершин, у которых есть хотя бы один ребёнок. Листы уже упорядочены.
   Поэтому начиная с n/2 до 0 все элементы просеиваются вниз.
3. Когда бинарная куча построена, чтобы не использовать дополнительную память для хранения отсортированного массива,
   производим перестановки на этом же массиве (куче). Начиная с последнего элемента каждый раз меняем с первым элементом и просеиваем его вниз.
   Так как последний элемент теперь будет на "своем" месте, то уменьшаем размер кучи на 1.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Построение кучи: (n/2 + (n-1)/2 + ... + (n-k)/2) = n/2 - в цикле, начиная с половины массива просеиваем вниз

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
O(N) - построение бинарной кучи
O(N) - сортировка in-place

N - кол-во элементов

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(N) - хранение бинарной кучи (массива)

N - кол-во элементов

Доп памяти для хранения отсортированного массива не требуется, так как изменяем кучу.
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

private fun <T> Array<T>.swap(i: Int, j: Int) {
	val temp = this[i]
	this[i] = this[j]
	this[j] = temp
}