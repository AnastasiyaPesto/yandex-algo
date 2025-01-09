package ru.zentsova.yandex.sprint3

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class IKtTest {

	@ParameterizedTest
	@MethodSource("arrayProvider")
	fun conferenceLovers(array: List<Int>, expected: List<Pair<Int, Int>>) {
		val actual = conferenceLovers(array)
		assertThat(actual).isEqualTo(expected)
	}

	companion object {
		@JvmStatic
		fun arrayProvider(): Stream<Arguments> {
			return Stream.of(
				Arguments.arguments(
					listOf(1, 2, 3, 1, 2, 3, 4),
					listOf(1 to 2, 2 to 2, 3 to 2, 4 to 1)
				),
				Arguments.arguments(
					listOf(1, 1, 1, 2, 2, 3),
					listOf(1 to 3, 2 to 2, 3 to 1)
				),
				Arguments.arguments(
					listOf(1, 2, 3, 1, 2, 3, 4, 4, 4, 4, 4, 4, 4),
					listOf(4 to 7, 1 to 2, 2 to 2, 3 to 2)
				),
				Arguments.arguments(
					emptyList<Int>(),
					emptyList<Pair<Int, Int>>()
				),
				Arguments.arguments(
					listOf(1),
					listOf(1 to 1)
				),
			)
		}
	}
}