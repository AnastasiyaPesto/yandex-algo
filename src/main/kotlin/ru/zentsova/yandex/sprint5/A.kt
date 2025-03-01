package ru.zentsova.yandex.sprint5

import java.util.*
import kotlin.math.max

// A. Лампочки

//// <template>
//class Node(var value: Int) {
//	var left: Node? = null
//	var right: Node? = null
//}
//// <template>
//
//fun treeSolution(head: Node?): Int {
//	if (head == null) return -1
//
//	var maxValue = -1
//
//	val stack = ArrayDeque<Node>()
//	stack.push(head)
//
//	while (stack.isNotEmpty()) {
//		val cur = stack.pop()
//		maxValue = maxOf(maxValue, cur.value)
//
//		cur.right?.let { stack.push(it) }
//		cur.left?.let { stack.push(it) }
//	}
//
//	return maxValue
//}
//
//fun test() {
//	val node1 = Node(1)
//	val node2 = Node(-5)
//	val node3 = Node(3)
//	node3.left = node1
//	node3.right = node2
//	val node4 = Node(2)
//	node4.left = node3
//	assert(treeSolution(node4) == 3)
//}