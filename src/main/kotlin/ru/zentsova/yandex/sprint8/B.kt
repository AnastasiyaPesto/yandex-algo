package ru.zentsova.yandex.sprint8

// B. Пограничный контроль
fun main() {
	val passportName = readln()
	val databaseName = readln()
	println(if (isAcceptable(passportName, databaseName)) "OK" else "FAIL")
}

fun isAcceptable(a: String, b: String): Boolean {
	val lenA = a.length
	val lenB = b.length

	// Равны — пропускаем
	if (a == b) return true

	// Разница в длине больше 1 — точно FAIL
	if (kotlin.math.abs(lenA - lenB) > 1) return false

	// Вариант 1: длины равны — проверим на одну замену
	if (lenA == lenB) {
		var diffCount = 0
		for (i in a.indices) {
			if (a[i] != b[i]) {
				diffCount++
				if (diffCount > 1) return false
			}
		}
		return true
	}

	// Вариант 2: разница в длине 1 — проверим на вставку/удаление
	// Убедимся, что `a` — более короткая строка
	val shorter = if (lenA < lenB) a else b
	val longer = if (lenA < lenB) b else a

	var i = 0
	var j = 0
	var foundDifference = false

	while (i < shorter.length && j < longer.length) {
		if (shorter[i] != longer[j]) {
			if (foundDifference) return false
			foundDifference = true
			j++ // пропускаем символ в длинной строке
		} else {
			i++
			j++
		}
	}

	return true
}
