package ru.zentsova.yandex.sprint3

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class FKtTest {

	@ParameterizedTest
	@MethodSource("arrayProvider")
	fun largestTrianglePerimeter(array: IntArray, expected: Int) {
		val actual = largestTrianglePerimeter(array)
		assertThat(actual).isEqualTo(expected)
	}

	companion object {
		@JvmStatic
		fun arrayProvider(): Stream<Arguments> {
			return Stream.of(
				Arguments.arguments(intArrayOf(6, 3, 3, 2), 8),
				Arguments.arguments(intArrayOf(5, 3, 7, 2, 8, 3), 20),
				Arguments.arguments(intArrayOf(1, 1), 0),
				Arguments.arguments(intArrayOf(), 0),
				Arguments.arguments(intArrayOf(2, 1, 1), 0),
				Arguments.arguments(intArrayOf(1, 1, 1), 3),
			)
		}
	}
}