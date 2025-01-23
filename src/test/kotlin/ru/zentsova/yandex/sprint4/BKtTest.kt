package ru.zentsova.yandex.sprint4

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class BKtTest {

	@ParameterizedTest
	@MethodSource("roundsProvider")
	fun largestContinuousSegment(rounds: IntArray, expected: Int) {
		val actual = largestContinuousSegment(rounds)
		assertThat(actual).isEqualTo(expected)
	}

	companion object {
		@JvmStatic
		fun roundsProvider(): Stream<Arguments> {
			return Stream.of(
				arguments(intArrayOf(0, 0, 1, 0, 1, 1, 1, 0, 0, 0), 8),
				arguments(intArrayOf(0, 0, 0, 1, 1, 1, 0, 0, 0, 0), 6),
				arguments(intArrayOf(0, 0, 1, 0, 0, 0, 1, 0, 0, 1), 4),
				arguments(intArrayOf(0, 1), 2),
				arguments(intArrayOf(0, 1, 0), 2),
				arguments(intArrayOf(0, 0, 0, 0, 0), 0),
				arguments(intArrayOf(1, 1, 1, 1, 1), 0),
			)
		}
	}
}