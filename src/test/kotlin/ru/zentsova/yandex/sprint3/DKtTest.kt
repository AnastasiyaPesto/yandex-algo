package ru.zentsova.yandex.sprint3

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class DKtTest {

	@ParameterizedTest
	@MethodSource("arraysProvider")
	fun numberSatisfiedChildren(factors: IntArray, children: IntArray, expected: Int) {
		val actual = numberSatisfiedChildren(factors, children)
		assertThat(actual).isEqualTo(expected)
	}

	companion object {
		@JvmStatic
		fun arraysProvider(): Stream<Arguments> {
			return Stream.of(
				Arguments.arguments(
					intArrayOf(1, 2),
					intArrayOf(2, 1, 3),
					2
				),
				Arguments.arguments(
					intArrayOf(2, 1, 3),
					intArrayOf(1, 1),
					1
				),
				Arguments.arguments(
					intArrayOf(1),
					intArrayOf(1),
					1
				),
				Arguments.arguments(
					intArrayOf(2, 5, 3, 6),
					intArrayOf(1, 1, 1),
					0
				),
				Arguments.arguments(
					intArrayOf(),
					intArrayOf(),
					0
				),
			)
		}
	}
}