package ru.zentsova.yandex.sprint5

import kotlin.math.max

// <template>
class Node(var value: Int) {
	var left: Node? = null
	var right: Node? = null
}
// <template>

fun treeSolution(head: Node?): Int {
	if (head == null) return 0

	val checkedVertices = mutableSetOf(1)
  var maxValue = head.value
	var left = head.left
	while (left != null) {
		maxValue = max(left.value, maxValue)
		checkedVertices.add(left.value)
		left = left.left
	}
}

fun test() {
	val node1 = Node(1)
	val node2 = Node(-5)
	val node3 = Node(3)
	node3?.left = node1
	node3?.right = node2
	val node4 = Node(2)
	node4?.left = node3
	assert(treeSolution(node4) == 3)
}