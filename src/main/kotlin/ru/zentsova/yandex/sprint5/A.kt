package ru.zentsova.yandex.sprint5

import java.util.*
import kotlin.math.max

// <template>
class Node(var value: Int) {
	var left: Node? = null
	var right: Node? = null
}
// <template>

fun treeSolution(head: Node?): Int {
	if (head == null) return -1

	val stack = Stack<Node>()
	stack.push(head)
	var maxValue = head.value
	var next = head.left
	while (stack.isNotEmpty() || next != null) {
		while (next != null && !stack.contains(next)) {
			maxValue = max(next.value, maxValue)
			stack.push(next)
			next = next.left
		}

		next = if (stack.size > 0) stack.pop().right else null
	}

	return maxValue
}

fun test() {
	val node1 = Node(1)
	val node2 = Node(-5)
	val node3 = Node(3)
	node3.left = node1
	node3.right = node2
	val node4 = Node(2)
	node4.left = node3
	assert(treeSolution(node4) == 3)
}