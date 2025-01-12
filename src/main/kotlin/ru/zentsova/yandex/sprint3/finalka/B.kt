package ru.zentsova.yandex.sprint3.finalka

/*
-- Спринт 3. Финалка. Б. Эффективная быстрая сортировка --
Ссылка на удачную посылку: https://contest.yandex.ru/contest/23815/run-report/131271817/

-- ПРИНЦИП РАБОТЫ --
Реализация быстрой сортировки "in-place".

1. Выбираю опорный элемент (в моем случае это элемент в середине массива)
2. В цикле (первый while) перемещаю элементы, которые "старше" опорного в левую часть, "младше или равные" - в правую.
2.1. Сначала двигаю индексы до нужных позиций (left и right). Элементы на этих местах нужно обменять местами (swap).
2.2. Обмениваю и сразу двигаю индекс left право, а индекс right влево, так как нет необходимости проверять только что обменянные элементы.
2.3. Продолжаю пока индексы left и right не пересекутся
3. Для каждой части массива вызываю метод рекурсивно.

Базовый случай:
Если левая граница массива (с которым работаем на текущем уровне рекурсии) больше или равна идексу right,
то левую часть массива сортировать больше не нужно.

Если правая граница массива (с которым работаем на текущем уровне рекурсии) меньше или равна идексу left,
то правую часть массива сортировать больше не нужно.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Алгоритм не требует дополнительной памяти для хранения массива с элементами больше опорного,
для массива с элементами равными опорному и массива с элементами меньше опорного,
так как меняются местами элементы в массиве, с которым работаем "на лету".

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
O(N*logN) - средний случай
O(N^2) - худший случай

Выбор pivot - O(1)
Глубина рекурсии - O(logN)
Распределить по левую и правую сторону от опорного элементы - O(N)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(N) - память только для хранения исходного массива.
Память на хранение переменные примитивных типов не учитываем (O(1))
 */

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
	val reader = BufferedReader(InputStreamReader(System.`in`))
	val internsCount = reader.readInt()
	val interns = reader.readInterns(internsCount)
	quickSortInPlace(interns, 0, interns.size - 1)
	interns.forEach { println(it.login) }
}

fun quickSortInPlace(interns: Array<Intern>, start: Int, end: Int) {
  val middle = start + (end - start) / 2
	val pivot = interns[middle]

	var left = start
	var right = end
	while (left < right) {
		while (comparator(interns[left], pivot)) left++
		while (!comparator(interns[right], pivot) && interns[right] != pivot) right--

		if (left <= right) {
			swap(interns, left, right)
			left++
			right--
		}
	}

	if (start < right) quickSortInPlace(interns, start, left)
	if (end > left) quickSortInPlace(interns, left, end)
}

fun swap(interns: Array<Intern>, i: Int, j: Int) {
	val tmp = interns[i]
	interns[i] = interns[j]
	interns[j] = tmp
}

private fun comparator(intern: Intern, pivot: Intern): Boolean {
	return if (intern.taskCount == pivot.taskCount) {
		if (intern.penaltyCount == pivot.penaltyCount) {
			intern.login < pivot.login
		} else {
			intern.penaltyCount < pivot.penaltyCount
		}
	} else {
		intern.taskCount > pivot.taskCount
	}
}

private fun BufferedReader.readInt() = readLine().toInt()

private fun BufferedReader.readInterns(n: Int) = (0 until n).map {
	val (login, tasksCount, penaltiesCount) = readLine().split(" ")
	Intern(
		login = login,
		taskCount = tasksCount.toInt(),
		penaltyCount = penaltiesCount.toInt(),
	)
}.toTypedArray()

data class Intern(
	val login: String,
	val taskCount: Int,
	val penaltyCount: Int,
)