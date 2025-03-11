package ru.zentsova.yandex.sprint5

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import java.util.stream.Stream.of

class MKtTest {

	@ParameterizedTest
	@MethodSource("maxHeapProvider")
	fun siftUpTest(heap: IntArray, idx: Int, newIndexExpected: Int) {
		assertThat(siftUp(heap, idx)).isEqualTo(newIndexExpected)
	}

	@ParameterizedTest
	@MethodSource("maxHeapProvider")
	fun siftUpLoopTest(heap: IntArray, idx: Int, newIndexExpected: Int) {
		assertThat(siftUpLoop(heap, idx)).isEqualTo(newIndexExpected)
	}


	companion object {
		@JvmStatic
		fun maxHeapProvider(): Stream<Arguments> = of(
			arguments(intArrayOf(-1, 12, 6, 8, 3, 15, 7), 5, 1),
			arguments(intArrayOf(-1, 12, 6, 8, 3, 10, 7), 5, 2),
			arguments(intArrayOf(-1, 15, 12, 8, 3, 6, 7), 5, 5),
		)
	}
}