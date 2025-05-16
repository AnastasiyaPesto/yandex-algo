package ru.zentsova.yandex.sprint8.finalka

/*
-- Спринт 8. Финалка. A. Packed Prefix --
Ссылка на удачную посылку:
https://new.contest.yandex.ru/contests/26133/problem?id=51450%2F2020_07_22%2FLxm8FGkQIC&tab=submissions&submissionId=10000068-278e-9948-952e-ef98bf9bc6a3

-- ПРИНЦИП РАБОТЫ --

1. Распаковка строк
- обрабатываем строку слева направо, начиная с позиции start (при считывании позиция 0)
- если встретили букву — добавляе её в текущий результат
- если видим число + [, запускаем функцию распаковки рекурсивно
- если встречаем ] — завершаем текущую вложенность и возвращаем результат в вызывающую функцию

2. Находим общий префикс
- начинаем с первой распакованной строки как базового префикса
- сравниваем её посимвольно со второй строкой, пока символы совпадают
- укорачиваем префикс до длины совпавшей части
- продолжаем сравнение с остальными строками
- если префикс становится пустым — завершаем сразу


-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --

Распаковка (unpack):
- буквы добавляются напрямую
- k[...] -> вложенность обрабатывается рекурсивно и повторяется k раз
- конкатенация (AB) разбирается последовательно
-> Работает корректно для любых вложенных запакованных строк

Поиск общего префикса (LCP):
- начинаем с первой строки, поочерёдно обрезаем префикс при несовпадении
- возвращаем максимальный общий префикс всех строк


-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
O(L) - где L  суммарная длина всех распакованных строк

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(L) - где L  суммарная длина всех распакованных строк

*/

fun main() {
	val n = readInt()
	val compressedStrings = List(n) { readln() }

	val unpackedStrings = compressedStrings.map { unpackFrom(it, 0).first }
	val prefix = longestCommonPrefix(unpackedStrings)
	println(prefix)
}

fun unpackFrom(s: String, start: Int): Pair<String, Int> {
	val sb = StringBuilder()
	var i = start

	while (i < s.length) {
		when {
			s[i].isLetter() -> {
				sb.append(s[i])
				i++
			}

			s[i].isDigit() -> {
				var num = 0
				while (s[i].isDigit()) {
					num = num * 10 + (s[i] - '0')
					i++
				}
				if (s[i] == '[') {
					i++
					val (sub, newPos) = unpackFrom(s, i)
					repeat(num) { sb.append(sub) }
					i = newPos
				}
			}

			s[i] == ']' -> {
				return Pair(sb.toString(), i + 1)
			}
		}
	}

	return Pair(sb.toString(), i)
}

fun longestCommonPrefix(strings: List<String>): String {
	if (strings.isEmpty()) return ""
	var prefix = strings[0]
	for (i in 1 until strings.size) {
		val current = strings[i]
		val minLength = minOf(prefix.length, current.length)
		var j = 0
		while (j < minLength && prefix[j] == current[j]) j++
		prefix = prefix.substring(0, j)
		if (prefix.isEmpty()) break
	}
	return prefix
}

private fun readInt() = readln().toInt()