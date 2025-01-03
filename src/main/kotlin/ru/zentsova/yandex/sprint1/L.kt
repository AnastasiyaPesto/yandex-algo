package ru.zentsova.yandex.sprint1

fun main() {
	val firstString = readln()
	val secondString = readln()
	val oddLetter = getOddLetter2(firstString, secondString)
	println(oddLetter)
}

private fun getOddLetter2(firstString: String, secondString: String): Char {
	val (bigger, smaller) = if (firstString.length < secondString.length) {
		secondString to firstString
	} else {
		firstString to secondString
	}

	var sum = 0
	bigger.forEach {
		sum += it.code
	}

	smaller.forEach {
		sum -= it.code
	}

	return sum.toChar()
}

private fun getOddLetter(firstString: String, secondString: String): Char {
	val (bigger, smaller) = if (firstString.length < secondString.length) {
		secondString to firstString
	} else {
		firstString to secondString
	}

	val map = mutableMapOf<Char, Int>().withDefault { 0 }
	smaller.forEach { char ->
		map[char] = map.getValue(char) + 1
	}

	for (i in bigger.indices) {
		val char = bigger[i]
		if (!map.contains(char)) {
			return char
		} else {
			val curCnt = map[char]!!.dec()
			if (curCnt < 0) {
				map.remove(char)
			} else {
				map[char] = curCnt
			}
		}
	}

	return ' '
}