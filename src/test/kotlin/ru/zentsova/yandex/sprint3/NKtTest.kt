package ru.zentsova.yandex.sprint3

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class NKtTest {

	@ParameterizedTest
	@MethodSource("sectionsProvider")
	fun flowerBeds(sections: List<Interval>, expected: List<Interval>) {
		val actual = flowerBeds(sections)
		assertThat(actual).isEqualTo(expected)
	}

	companion object {
		@JvmStatic
		fun sectionsProvider(): Stream<Arguments> {
			return Stream.of(
				arguments(
					listOf(Interval(1, 4), Interval(5, 7)),
					listOf(Interval(1, 4), Interval(5, 7)),
				),
				arguments(
					listOf(Interval(1, 2), Interval(1, 7), Interval(3, 8), Interval(2, 5)),
					listOf(Interval(1, 8)),
				),
				arguments(
					listOf(Interval(7, 8), Interval(7, 8), Interval(2, 3), Interval(6, 10)),
					listOf(Interval(2, 3), Interval(6, 10)),
				),
				arguments(
					listOf(Interval(9, 11), Interval(3, 6), Interval(6, 7), Interval(1, 4), Interval(1, 5), Interval(2, 5), Interval(8, 10)),
					listOf(Interval(1, 7), Interval(8, 11)),
				),
				arguments(
					listOf(Interval(1, 3), Interval(3, 5), Interval(4, 6), Interval(5, 6), Interval(2, 4), Interval(7, 10)),
					listOf(Interval(1, 6), Interval(7, 10)),
				),
				arguments(
					listOf(
						Interval(4, 13), Interval(48, 68), Interval(42, 71), Interval(6, 43),
						Interval(17, 20), Interval(43, 71), Interval(42, 89), Interval(20, 31), Interval(0, 55)
					),
					listOf(Interval(0, 89)),
				),
				arguments(listOf(Interval(1, 1), Interval(1, 1)), listOf(Interval(1, 1))),
				arguments(emptyList<Interval>(), emptyList<Interval>()),
			)
		}
	}
}