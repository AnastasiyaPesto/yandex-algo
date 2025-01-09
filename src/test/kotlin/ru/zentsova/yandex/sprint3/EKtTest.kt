package ru.zentsova.yandex.sprint3

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class EKtTest {

	@ParameterizedTest
	@MethodSource("arrayProvider")
	fun largestNumberHousesToBuy(houseCosts: IntArray, money: Int, expected: Int) {
		val actual = largestNumberHousesToBuy(houseCosts, money)
		assertThat(actual).isEqualTo(expected)
	}

	companion object {
		@JvmStatic
		fun arrayProvider(): Stream<Arguments> {
			return Stream.of(
				Arguments.arguments(intArrayOf(999, 999, 999), 300, 0),
				Arguments.arguments(intArrayOf(350, 999, 200), 1000, 2),
				Arguments.arguments(intArrayOf(100, 100, 200, 300), 500, 3),
				Arguments.arguments(intArrayOf(100, 100, 200, 100), 500, 4),
				Arguments.arguments(intArrayOf(), 500, 0),
				Arguments.arguments(intArrayOf(100), 100, 1),
			)
		}
	}
}