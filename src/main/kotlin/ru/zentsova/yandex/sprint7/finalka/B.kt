package ru.zentsova.yandex.sprint7.finalka

/*
-- Спринт 7. Финалка. B. Одинаковые суммы --
Ссылка на удачную посылку: https://contest.yandex.ru/contest/25597/run-report/137753149/

-- ПРИНЦИП РАБОТЫ --

1). Считаем сумму всех очков.
2). Если сумма нечётная — сразу возвращаем false.
3). Иначе, ищем, существует ли подмножество с суммой totalSum / 2 (это и будет target).
4). В dp храним булевы значения. dp[i] == true - значит сумму i собрать можно из подмножества.

Цикл от конца к началу, чтобы не использовать одно и то же число дважды.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --

Перебираем все возможные суммы, которые можно составить из любых подмножеств очков,
и запоминаем, какие из них достижимы. Если нужная сумма достижима — задача решена.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --

O(nk) - где n длина массива, k - целевое число (target)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --

O(k) - где k это target, для доп хранения массива dp

*/

fun main() {
	readInt()
	val partyPoints = readInts()
	val canBeDivided = canBeDivided(partyPoints)
	println(if (canBeDivided) "True" else "False")
}

fun canBeDivided(partyPoints: List<Int>): Boolean {
	val totalSum = partyPoints.sum()
	if (totalSum % 2 != 0) return false

	val target = totalSum / 2
	val dp = BooleanArray(target + 1)
	dp[0] = true

	for (point in partyPoints) {
		for (j in target downTo point) {
			dp[j] = dp[j] || dp[j - point]
		}
	}

	return dp[target]
}

private fun readInt() = readln().toInt()
private fun readInts() = readln().split(" ").map { it.toInt() }