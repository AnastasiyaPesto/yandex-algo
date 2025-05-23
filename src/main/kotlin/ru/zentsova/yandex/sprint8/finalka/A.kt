package ru.zentsova.yandex.sprint8.finalka

/*
-- Спринт 8. Финалка. A. Packed Prefix --
ID удачной посылки: 138633410

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
O(L) - где L суммарная длина всех распакованных строк
- рекурсивная распаковка - работа функции пропорциональна длине распакованной строки
O(L1 + L1 + ... + Ln) = O(L)
- поиск префикса
в худшем случае проходим по всей длине префикса между строками =>
O(L) общий префикс не может быть длиннее полной распакованной строки


-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(L) - где L суммарная длина всех распакованных строк
после распаковки в списке unpackedStrings — n строк общей длиной L
в каждый момент времени sb используется для одной строки, и после этого превращается в String => O(L)
*/

fun main() {
	val n = readInt()
	val unpackedStrings = Array(n) {
		val str = readln()
		buildString { str.unpackFrom(0, this) }
	}

	val prefix = longestCommonPrefix(unpackedStrings)
	println(prefix)
}

fun String.unpackFrom(start: Int, sb: StringBuilder): Int {
	var i = start

	while (i < length) {
		when {
			this[i].isLetter() -> {
				sb.append(this[i])
				i++
			}

			this[i].isDigit() -> {
				val num = this[i].digitToInt()
				val innerStart = sb.length
				i = unpackFrom(i + 2, sb)
				val inner = sb.subSequence(innerStart, sb.length)
				repeat(num - 1) { sb.append(inner) }
			}

			this[i] == ']' -> {
				return i + 1
			}
		}
	}

	return i
}

fun longestCommonPrefix(strings: Array<String>): String {
	if (strings.isEmpty()) return ""

	val first = strings[0]
	var prefixLength = first.length

	for (i in 1 until strings.size) {
		val current = strings[i]
		val minLength = minOf(prefixLength, current.length)
		var j = 0
		while (j < minLength && first[j] == current[j]) j++
		prefixLength = j
		if (prefixLength == 0) break
	}

	return first.substring(0, prefixLength)
}

private fun readInt() = readln().toInt()