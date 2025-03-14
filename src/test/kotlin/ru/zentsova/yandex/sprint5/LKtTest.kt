package ru.zentsova.yandex.sprint5

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import java.util.stream.Stream.of

class LKtTest {

	@ParameterizedTest
	@MethodSource("maxHeapProvider")
	fun siftDownTest(heap: IntArray, heapExpected: IntArray, idx: Int, newIndexExpected: Int) {
		assertThat(siftDown(heap, idx)).isEqualTo(newIndexExpected)
		assertThat(heap).isEqualTo(heapExpected)
	}

	companion object {
		@JvmStatic
		fun maxHeapProvider(): Stream<Arguments> = of(
			arguments(intArrayOf(-1, 12, 1, 8, 3, 4, 7), intArrayOf(-1, 12, 4, 8, 3, 1, 7), 2, 5),
			arguments(intArrayOf(-1, 12, 1, 8, 3, 4, 7), intArrayOf(-1, 12, 4, 8, 3, 1, 7), 2, 5),
		)
	}
}