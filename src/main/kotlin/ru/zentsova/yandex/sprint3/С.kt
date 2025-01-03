package ru.zentsova.yandex.sprint3

fun main() {
	val s = readln()
	val t = readln()
	println(if (subsequence(s, t)) "True" else "False")
}

private fun subsequence(substring: String, line: String): Boolean {
	if (substring.length > line.length) return false

	var j = 0
	val sb = StringBuilder()
	for (i in substring.indices) {
		while (j < line.length) {
			if (substring[i] == line[j]) {
				sb.append(line[j])
				j++
				break
			}
			j++
		}

		if (substring.length == sb.length) break
	}

	return substring == sb.toString()
}



//for (i in line.indices) {
//	for (j in i until substring.length) {
//		if (line[j] == substring[i]) {
//			sb.append(line[j])
//			break
//		}
//	}
//	if (substring.length == sb.length) break
//}