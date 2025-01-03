package ru.zentsova.yandex.sprint2

// // https://contest.yandex.ru/contest/22779/problems/C/ - С. Нелюбимое дело
//
//// <template>
//class Node<V>(var value: V, var next: Node<V>?)
//// <template>
//
//fun solution(head: Node<String>?, pos: Int): Node<String>? {
//  if (pos < 0) return head
//
//  var newHead = head
//  if (pos == 0 && head != null) {
//    newHead = head.next
//    head.next = null
//    return newHead
//  }
//
//  val node = getNodeByIndex(newHead, pos)
//  if (node != newHead) {
//    val prevNode = getNodeByIndex(newHead, pos - 1)
//    prevNode?.next = node?.next
//    node?.next = null
//  }
//  return newHead
//}
//
//private fun getNodeByIndex(node: Node<String>?, index: Int): Node<String>? {
//  var i = index
//  var current = node
//  while (i > 0 && current != null) {
//    current = current.next
//    i--
//  }
//  return current
//}
//
//fun test() {
//  val node3 = Node("node3", null)
//  val node2 = Node("node2", node3)
//  val node1 = Node("node1", node2)
//  val node0 = Node("node0", node1)
//  val newHead = solution(node0, 1)
//  assert(newHead === node0)
//  assert(newHead?.next === node2)
//  assert(newHead?.next?.next === node3)
//  assert(newHead?.next?.next?.next == null)
  // result is : node0 -> node2 -> node3
//}
