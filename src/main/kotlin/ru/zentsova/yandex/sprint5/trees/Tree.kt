package ru.zentsova.yandex.sprint5.trees

data class Node(
	var value: Int,
	var left: Node? = null,
	var right: Node? = null,
)

fun insert(root: Node?, key: Int): Node {
	if (root === null) return Node(value = key)

	when {
		key < root.value -> root.left = insert(root.left, key)
		key >= root.value -> root.right = insert(root.right, key)
	}

	return root
}

fun main() {
	val root = Node(value = 10)
	root.left = Node(5)
	root.right = Node(11)

	insert(root, 3)
}
