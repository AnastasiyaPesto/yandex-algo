package ru.zentsova.yandex.sprint5

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class IKtTest {

	@Test
	fun countBinarySearchTreesTest() {
    assertThat(countBinarySearchTrees(2)).isEqualTo(2)
    assertThat(countBinarySearchTrees(3)).isEqualTo(5)
    assertThat(countBinarySearchTrees(4)).isEqualTo(14)
	}
}