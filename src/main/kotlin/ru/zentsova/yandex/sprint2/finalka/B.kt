package ru.zentsova.yandex.sprint2.finalka

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Math.floorDiv
import java.util.Stack
import java.util.StringTokenizer

fun main() {
	val reader = BufferedReader(InputStreamReader(System.`in`))
	val task = StringTokenizer(reader.readLine())
	val stack = Stack<String>()
	while (task.hasMoreElements()) {
		val cur = task.nextToken()
		if (cur.length == 1 && !cur[0].isDigit()) {
			val num1 = stack.pop().toInt()
			val num2 = stack.pop().toInt()
			stack.push(
				when (cur) {
					"+" -> num2 + num1
					"-" -> num2 - num1
					"*" -> num2 * num1
					"/" -> floorDiv(num2, num1)
					else -> throw UnsupportedOperationException()
				}.toString()
			)
		} else {
			stack.push(cur)
		}
	}
	println(stack.peek())
}