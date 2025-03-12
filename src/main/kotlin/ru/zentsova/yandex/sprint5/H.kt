//package ru.zentsova.yandex.sprint5
//
//// H. Числовые пути
//
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
//fun treeSolution(head: Node?): Int {
//	val allPaths = mutableListOf<Int>()
//	path(head, "", allPaths)
//	return allPaths.sumOf { it }
//}
//
//fun path(node: Node?, number: String, outputBuffer: MutableList<Int>) {
//	if (node === null) return
//
//	val current = "$number${node.value}"
//
//	if (node.left === null && node.right === null) {
//		outputBuffer.add(current.toInt())
//		return
//	}
//
//	path(node.left, current, outputBuffer)
//	path(node.right, current, outputBuffer)
//}
//
//private fun main() {
//	val node1 = Node(2, null, null)
//	val node2 = Node(1, null, null)
//	val node3 = Node(3, node1, node2)
//	val node4 = Node(2, null, null)
//	val node5 = Node(1, node4, node3)
//	assert(treeSolution(node5) == 275)
//}