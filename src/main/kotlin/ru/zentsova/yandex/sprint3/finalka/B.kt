package ru.zentsova.yandex.sprint3.finalka

/*
-- Спринт 3. Финалка. Б. Эффективная быстрая сортировка --
Ссылка на удачную посылку: https://contest.yandex.ru/contest/23815/run-report/131271817/
Ссылка на удачную посылку (поcле 1-ого ревью):
https://contest.yandex.ru/contest/23815/run-report/131420626/

-- ПРИНЦИП РАБОТЫ --
Реализация быстрой сортировки "in-place".

1. Выбираю опорный элемент (в моем случае это элемент в середине массива)
2. В цикле (первый while) перемещаю элементы, которые "старше" опорного в левую часть, "младше или равные" - в правую.
2.1. Сначала двигаю индексы до нужных позиций (left и right). Элементы на этих местах нужно обменять местами (swap).
2.2. Обмениваю элементы местами
2.3. Продолжаю пока индексы left и right не пересекутся
3. Для каждой части массива вызываю метод рекурсивно.

Базовый случай:
Если начало дальше конца (или равно)

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Алгоритм не требует дополнительной памяти для хранения массива с элементами больше опорного,
для массива с элементами равными опорному и массива с элементами меньше опорного,
так как меняются местами элементы в массиве, с которым работаем "на лету".

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
O(N*logN) - средний случай

Выбор pivot - O(1)
Глубина рекурсии - O(logN)
Распределить по левую и правую сторону от опорного элементы - O(N)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(N) - память только для хранения исходного массива.
Память на хранение переменные примитивных типов не учитываем (O(1))
*/

import java.io.BufferedReader

fun main() {
	val reader = System.`in`.bufferedReader()
	val internsCount = reader.readInt()
	val interns = reader.readInterns(internsCount)
	interns.quickSortInPlace(0, interns.lastIndex, comparator())
	print(interns.joinToString(separator = "\n") { it.login })
}

fun <T> Array<T>.quickSortInPlace(start: Int, end: Int, comparator: Comparator<in T>) {
	if (isEmpty() || start >= end) return

	val pivot = get((start..end).random())

	var left = start
	var right = end
	while (left < right) {
		while (comparator.compare(get(left), pivot) < 0) left++
		while (comparator.compare(get(right), pivot) > 0) right--

		if (left <= right) swap(left, right)
	}

	quickSortInPlace(start, left, comparator)
	quickSortInPlace(left + 1, end, comparator)
}

private fun <T> Array<T>.swap(i: Int, j: Int) {
	val tmp = this[i]
	this[i] = this[j]
	this[j] = tmp
}

private fun comparator() =
	compareByDescending<Intern> { it.taskCount }.thenBy { it.penaltyCount }.thenBy { it.login }

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