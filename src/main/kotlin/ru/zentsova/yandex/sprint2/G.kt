//package ru.zentsova.yandex.sprint2
////
//
//import kotlin.math.max
//
//class StackEffective {
//	private var current: Node? = null
//	private var maxValue: Node? = null
//
//	fun push(value: Int) {
//		if (current == null) {
//			current = Node(value)
//			maxValue = Node(value)
//		} else {
//			current = Node(value = value, next = null, prev = current)
//			current!!.prev!!.next = current
//			maxValue = Node(value = max(value, maxValue!!.value), next = null, prev = maxValue)
//			maxValue!!.prev!!.next = maxValue
//		}
//	}
//
//	fun pop(): Int? {
//		if (current == null) return null
//
//		val res = current!!.value
//		current = current!!.prev
//		maxValue = maxValue!!.prev
//		return res
//	}
//
//	fun top(): Int? = current?.value
//
//	fun getMax(): Int? {
//		return maxValue?.value
//	}
//	class Node(var value: Int, var next: Node? = null, var prev: Node? = null)
//}
//
//fun main() {
//	val stack = StackEffective()
//	val commandCnt = readInt()
//	for (i in 0 until commandCnt) {
//		val commands = readStrings()
//		if (commands[0] == "push") {
//			stack.push(commands[1].toInt())
//			continue
//		}
//		when (commands[0]) {
//			"pop" -> stack.pop() ?: println("error")
//			"get_max" -> println(stack.getMax() ?: "None")
//			"top" -> println(stack.top() ?: "error")
//		}
//	}
//}
//
//private fun readStr() = readln()
//
//private fun readInt() = readStr().toInt()
//
//private fun readStrings() = readStr().split(" ")