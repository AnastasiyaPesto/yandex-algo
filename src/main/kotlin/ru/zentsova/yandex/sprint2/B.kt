//package ru.zentsova.yandex.sprint2
//
//// https://contest.yandex.ru/contest/22779/problems/B/ - B. Список дел
//
//// <template>
//class Node<V>(
//  var value: V,
//  var next: Node<V>? = null,
//)
//// <template>
//
//fun solution(head: Node<String>?) {
//  var current = head
//  while (current != null) {
//    println(current.value)
//    current = current.next
//  }
//}
//
//fun test() {
//  val node3 = Node("node3")
//  val node2 = Node("node2", node3)
//  val node1 = Node("node1", node2)
//  val node0 = Node("node0", node1)
//  solution(node0)
//  /*
//  Output is:
//  node0
//  node1
//  node2
//  node3
//  */
//}