//package ru.zentsova.yandex.sprint5
//
//// J. Добавь узел
//
//// <template>
//class Node(var left: Node?, var right: Node?, var value: Int)
//// <template>
//
//fun insert(root: Node, key: Int): Node {
//	return insertNode(root, key)
//}
//
//fun insertNode(root: Node?, key: Int): Node {
//	if (root == null)
//		return Node(value = key, left = null, right = null)
//
//	if (key >= root.value) {
//		root.right = insertNode(root.right, key)
//	} else {
//		root.left = insertNode(root.left, key)
//	}
//
//	return root
//}
//
//fun main() {
//	val node1 = Node(null, null, 7)
//	val node2 = Node(node1, null, 8)
//	val node3 = Node(null, node2, 7)
//	val newHead = insert(node3, 6)
//	assert(newHead == node3)
//	assert(newHead?.left!!.value == 6)
//}
