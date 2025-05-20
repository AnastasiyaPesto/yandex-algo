package ru.zentsova.yandex.sprint8.finalka

/*
-- Спринт 8. Финалка. B. Шпаргалка --
ID удачной посылки: 138631825

-- ПРИНЦИП РАБОТЫ --
Используем динамическое программирование и префиксное дерево (бор).
dp[i] = true, если префикс t[0..i-1] можно разбить на слова из словаря.
Из каждой позиции i, где dp[i] = true, идём по бору и отмечаем все возможные окончания слов.
Ответ — dp[t.length].

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Алгоритм корректен, так как перебирает все возможные разбиения строки t,
помечая dp[i] = true, только если до этого была достижима корректная разбивка и найдено слово в боре.
Если dp[t.length] = true, строка разбивается полностью.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
O(Lm) - где L - длина строки t, m - максимальная длина слова в словаре

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(LS) - где L - длина строки t (хранения dp), S — суммарная длина всех слов в словаре (размер бора)
*/

fun main() {
	val line = readln()
	val n = readInt()
	val trie = Trie()
	repeat(n) { trie.add(readln()) }
	println(if (canSplitText(line, trie)) "YES" else "NO")
}

fun canSplitText(line: String, trie: Trie): Boolean {
	val n = line.length
	val dp = BooleanArray(n + 1)
	dp[0] = true

	for (i in 0 until n) {
		if (!dp[i]) continue
		for (end in trie.findWordsFrom(line, i)) {
			dp[end] = true
		}
	}

	return dp[n]
}

private fun readInt() = readln().toInt()

class Trie {
	private val root = TrieNode()

	fun add(word: String) {
		var current = root
		for (char in word) {
			current = current.children.getOrPut(char) { TrieNode() }
		}
		current.isTerminal = true
	}

	fun findWordsFrom(line: String, start: Int): List<Int> {
		val result = mutableListOf<Int>()
		var current = root
		for (i in start until line.length) {
			val char = line[i]
			current = current.children[char] ?: break
			if (current.isTerminal) {
				result.add(i + 1)
			}
		}
		return result
	}

	private data class TrieNode(
		val children: MutableMap<Char, TrieNode> = mutableMapOf(),
		var isTerminal: Boolean = false
	)
}