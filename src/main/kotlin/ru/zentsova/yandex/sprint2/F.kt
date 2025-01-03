//package ru.zentsova.yandex.sprint2
//
//import kotlin.math.max
//
//class Stack {
//	private val items = mutableListOf<Int>()
//	var maxValue: Int? = null
//
//	fun push(item: Int) {
//		items.add(item)
//		maxValue = calcMaxValue(maxValue, item)
//	}
//
//	fun pop(): Int? {
//		if (items.size == 0) return null
//
//		val lastValue = items.removeLast()
//		maxValue = null
//		for (item in items) {
//			maxValue = calcMaxValue(maxValue, item)
//		}
//		return lastValue
//	}
//
//	private fun calcMaxValue(value: Int?, item: Int) =
//		if (value != null) {
//			max(value, item)
//		} else {
//			item
//		}
//}
//
//fun main() {
//	val stack = Stack()
//	val commandCnt = readInt()
//	for (i in 0 until commandCnt) {
//		val commands = readStrings()
//		if (commands[0] == "push") {
//			stack.push(commands[1].toInt())
//			continue
//		}
//		when (commands[0]) {
//			"pop" -> stack.pop() ?: println("error")
//			"get_max" -> println(stack.maxValue ?: "None")
//		}
//	}
//}
//
//private fun readStr() = readln()
//
//private fun readInt() = readStr().toInt()
//
//private fun readStrings() = readStr().split(" ")