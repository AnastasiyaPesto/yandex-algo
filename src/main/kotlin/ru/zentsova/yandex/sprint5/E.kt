//package ru.zentsova.yandex.sprint5
//
//import kotlin.Int.Companion.MAX_VALUE
//import kotlin.Int.Companion.MIN_VALUE
//
//// E. Дерево поиска
//// <template>
//class Node {
//	var value: Int
//	var left: Node?
//	var right: Node?
//
//	constructor(value: Int) {
//		this.value = value
//		right = null
//		left = null
//	}
//
//	constructor(value: Int, left: Node?, right: Node?) {
//		this.value = value
//		this.left = left
//		this.right = right
//	}
//}
//// <template>
//
//fun treeSolution(head: Node?): Boolean = check(head, MIN_VALUE, MAX_VALUE)
//
//fun check(head: Node?, min: Int, max: Int): Boolean = when {
//	head === null -> true
//	head.value <= min || head.value >= max -> false
//	else -> check(head.left, min, head.value) && check(head.right, head.value, max)
//}
//
//fun test() {
//	val node1 = Node(1, null, null)
//	val node2 = Node(4, null, null)
//	val node3 = Node(3, node1, node2)
//	val node4 = Node(8, null, null)
//	val node5 = Node(5, node3, node4)
//	assert(treeSolution(node5))
//	node2.value = 5
//	assert(!treeSolution(node5))
//}