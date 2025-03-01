package ru.zentsova.yandex.sprint5.trees

class AvlTreeNode(
	var key: Int,
//	var balanceFactor: Int = 0,
	var height: Int = 1,
	var left: AvlTreeNode? = null,
	var right: AvlTreeNode? = null,
	var parent: AvlTreeNode? = null,
)