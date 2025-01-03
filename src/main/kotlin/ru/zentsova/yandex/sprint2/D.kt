//package ru.zentsova.yandex.sprint2
//
//// https://contest.yandex.ru/contest/22779/problems/D/ - D. Заботливая мама
//
//// <template>
//class Node<V>(var value: V, var next: Node<V>?) {}
//// <template>
//
//fun solution(head: Node<String>?, item: String): Int {
//	var curNode = head
//	var idx = -1
//	while (curNode != null) {
//		idx++
//		if (curNode.value == item) return idx
//		curNode = curNode.next
//	}
//	return -1
//}
//
//fun test() {
//	val node3 = Node("node3", null)
//	val node2 = Node("node2", node3)
//	val node1 = Node("node1", node2)
//	val node0 = Node("node0", node1)
//	val idx: Int = solution(node0, "node2")
//	assert(idx == 2)
//}