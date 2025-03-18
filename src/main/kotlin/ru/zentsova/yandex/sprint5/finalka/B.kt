package ru.zentsova.yandex.sprint5.finalka

/*
-- Спринт 5. Финалка. B. Удали узел --
Ссылка на удачную посылку: https://contest.yandex.ru/contest/24810/run-report/135033001/
Ссылка после исправлений 1-го ревью: https://contest.yandex.ru/contest/24810/run-report/135144448/

-- ПРИНЦИП РАБОТЫ --
1. Ищем узел, который необходимо удалить (сравнивая value и key)
Если искомый ключ меньше, чем значение в текущей вершине, то рекурсивно вызываем метод remove() для левого поддерева.
Если искомый ключ больше, чем значение в текущей вершине, то рекурсивно вызываем метод remove() для правого поддерева.
Если искомый ключ равен значению в текущей вершине, то начинаем удаление.

2. Если найденная вершина является листом, то перенаправляем ссылку на эту вершину на null

3. Если вершина не является листом, то смотрим на детей
- Если есть левая дочерняя вершина и нет правой дочерней вершины,
  то перенаправляем ссылку на эту вершину на левую дочернюю вершину.
- Если есть правая дочерняя вершина и нет левой дочерней вершины,
  то перенаправляем ссылку на эту вершину на правую дочернюю вершину.
- Если есть обе дочерних вершины, то ище вершину с максимальным значением в левом поддереве
  (то есть самую правую вершину из левого поддерева).
  Перезаписываем значение. И удаляем вершину, которая была найдена как максимум.

4. Если ссылка, передаваемая в качестве аргумента в метод remove() равна null, то возвращаем null.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --

После удаления вершины, BST остается корректным, так как все ссылки перепревязываются при необходимости.
Дерево не распадается на поддеревья.

Когда у вершины есть только один ребенок, то можно родителю удаляемой вершины, в качестве потомка указать этого единственного потомка удаляемой вершины,
так как потомок удаляемой вершины, меньше, чем удаляемая вершина, следовательно, и меньше, чем родитель.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --

O(H), где H — высота дерева, где H = logN (N - количество элементов в бинарном дереве поиска)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --

O(N), где N - количество элементов.
Так как используем рекурсию, то на каждом уровне рекурсии храним информацию о предыдущем узле.
*/

// <template>
class Node(var left: Node?, var right: Node?, var value: Int)
// <template>

fun remove(root: Node?, key: Int): Node? {
	if (root === null) return null

	var vertex = root
	when {
		(key < vertex.value) -> vertex.left = remove(vertex.left, key)
		(key > vertex.value) -> vertex.right = remove(vertex.right, key)
		else -> vertex = removeNode(root)
	}
	return vertex
}

fun vertexWithMaxValue(root: Node): Node = root.right?.let { vertexWithMaxValue(it) } ?: root

fun removeNode(vertex: Node): Node? = when {
	vertex.left !== null && vertex.right !== null -> {
		vertex.value = vertexWithMaxValue(vertex.left!!).value
		vertex.left = remove(vertex.left, vertex.value)
		vertex
	}
	vertex.left !== null -> vertex.left
	vertex.right !== null -> vertex.right
	else -> null
}

fun test() {
	val node1 = Node(null, null, 2)
	val node2 = Node(node1, null, 3)
	val node3 = Node(null, node2, 1)
	val node4 = Node(null, null, 6)
	val node5 = Node(node4, null, 8)
	val node6 = Node(node5, null, 10)
	val node7 = Node(node3, node6, 5)
	val newHead = remove(node7, 10)
	assert(newHead!!.value == 5)
	assert(newHead?.right == node5)
	assert(newHead?.right!!.value == 8)
}