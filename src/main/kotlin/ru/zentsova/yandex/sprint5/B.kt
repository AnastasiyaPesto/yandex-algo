//package ru.zentsova.yandex.sprint5
//
//// B. Сбалансированное дерево
//
//import kotlin.math.abs
//import kotlin.math.max
//
//// <template>
//class Node(var value: Int) {
//	var left: Node? = null
//	var right: Node? = null
//}
//// <template>
//
//fun treeSolution(head: Node?): Boolean = checkHeight(head) != -1
//
//private fun checkHeight(node: Node?): Int {
//	if (node === null) return 0
//
//	val left = checkHeight(node.left)
//	if (left == -1) return -1
//
//	val right = checkHeight(node.right)
//	if (right == -1) return -1
//
//	if (abs(left - right) > 1) return -1 // дерево не сбалансировано
//
//	return max(left, right) + 1
//}
//
//fun test() {
//	val node1 = Node(1)
//	val node2 = Node(-5)
//	val node3 = Node(3)
//	node3?.left = node1
//	node3?.right = node2
//	val node4 = Node(10)
//	val node5 = Node(2)
//	node5?.left = node3
//	node5?.right = node4
//	assert(treeSolution(node5))
//}