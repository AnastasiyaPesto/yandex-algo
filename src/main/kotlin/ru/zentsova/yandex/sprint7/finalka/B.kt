package ru.zentsova.yandex.sprint7.finalka

/*
-- Спринт 7. Финалка. B. Одинаковые суммы --
Ссылка на удачную посылку: https://contest.yandex.ru/contest/25597/run-report/138007104/

-- ПРИНЦИП РАБОТЫ --

1. Что будет храниться в массиве dp?

dp[i] — можно ли набрать сумму i из подмножества очков.

2. Каким будет базовый случай для задачи?

dp[0] = true - сумму 0 всегда можно набрать (пустым множеством).

3. Каким будет переход динамики?

dp[j] = dp[j] || dp[j - point]
(если сумма j - point достижима, то и сумма j достижима при добавлении point)

4. Каким будет порядок вычисления данных в массиве dp?

Внешний цикл по point, внутренний — по j от target до point.

5. Где будет располагаться ответ на исходный вопрос?

dp[target] == true - можно разбить очки на две равные части.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --

Мы инициализируем dp[0] = true (сумма 0 достижима пустым подмножеством)
и последовательно обрабатываем каждое значение, обновляя возможные суммы снизу вверх.
Если в итоге dp[target] == true, то существует подмножество, сумма которого равна totalSum / 2.
Это означает, что остальное подмножество автоматически даст вторую половину, и разбиение возможно.

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
		if (dp[target]) return true
	}

	return dp.last()
}

private fun readInt() = readln().toInt()
private fun readInts() = readln().split(" ").map { it.toInt() }