package ru.zentsova.yandex.sprint8.finalka

/*
-- Спринт 8. Финалка. B. Шпаргалка --
Ссылка на удачную посылку:
https://new.contest.yandex.ru/contests/26133/problem?id=51450%2F2020_07_21%2Fq5eMaCPDPy&tab=submissions&submissionId=10000068-2792-66ab-d279-d9f7879089c6

-- ПРИНЦИП РАБОТЫ --
Алгоритм проверяет, можно ли разбить строку t на слова из словаря, используя динамическое программирование:
dp[i] = true, если префикс t[0..i-1] можно разбить на слова из словаря.
Начинаем с dp[0] = true (пустая строка).
Для каждого индекса i пробуем все слова: если слово подходит к позиции i и префикс перед ним разбиваемый, ставим dp[i] = true.
Ответ — значение dp[t.length]: можно ли разбить всю строку.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Алгоритм корректен, потому что он перебирает все возможные разбиения строки t на суффиксы,
соответствующие словам из словаря, и использует dp для хранения информации о допустимости каждого префикса.
Если dp[i] = true, это значит, что строку t[0..i-1] можно разбить на слова из словаря,
и это достигается только если ранее построенные корректные разбиения тоже верны.
Таким образом, если dp[t.length] = true, значит, существует последовательность слов, полностью покрывающая t.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
O(Lnw) - где L - длина строки t, n - кол-во слов в словаре, w - максимальная длина слова в словаре

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(L) -  где L - длина строки t (хранения dp)

*/

fun main() {
	val line = readln()
	val n = readInt()
	val dictionary = List(n) { readln() }

	println(if (canSplitText(line, dictionary)) "YES" else "NO")
}

fun canSplitText(line: String, dictionary: List<String>): Boolean {
	val n = line.length
	val dp = BooleanArray(n + 1)
	dp[0] = true

	for (i in 1..n) {
		for (word in dictionary) {
			val len = word.length
			if (i - len >= 0 && dp[i - len]) {
				if (line.substring(i - len, i) == word) {
					dp[i] = true
					break
				}
			}
		}
	}
	return dp[n]
}

private fun readInt() = readln().toInt()