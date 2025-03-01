package ru.zentsova.yandex.sprint5

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EKtTest {

	@Test
	fun treeSolution() {
		val node1 = Node(1, null, null)
		val node2 = Node(4, null, null)
		val node3 = Node(3, node1, node2)
		val node4 = Node(8, null, null)
		val node5 = Node(5, node3, node4)

		assertThat(treeSolution(node5)).isTrue()

		node2.value = 5
		assertThat(treeSolution(node5)).isFalse()
	}
}