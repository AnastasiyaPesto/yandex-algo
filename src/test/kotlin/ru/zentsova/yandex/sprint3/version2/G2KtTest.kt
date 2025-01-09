package ru.zentsova.yandex.sprint3.version2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class G2KtTest {

	@ParameterizedTest
	@MethodSource("arrayProvider")
	fun wardrobe(input: IntArray, expected: IntArray) {
		val actual = wardrobe(input)
		assertThat(actual).isEqualTo(expected)
	}

	companion object {
		@JvmStatic
		fun arrayProvider(): Stream<Arguments> {
			return Stream.of(
				Arguments.arguments(intArrayOf(0, 2, 1, 2, 0, 0, 1), intArrayOf(0, 0, 0, 1, 1, 2, 2)),
				Arguments.arguments(intArrayOf(2, 1, 1, 2, 0, 2), intArrayOf(0, 1, 1, 2, 2, 2)),
				Arguments.arguments(intArrayOf(1, 2, 0, 1, 1, 0, 2, 0), intArrayOf(0, 0, 0, 1, 1, 1, 2, 2)),
				Arguments.arguments(intArrayOf(0, 0, 0, 0), intArrayOf(0, 0, 0, 0)),
				Arguments.arguments(intArrayOf(1, 1, 1, 1), intArrayOf(1, 1, 1, 1)),
				Arguments.arguments(intArrayOf(2, 2, 2, 2), intArrayOf(2, 2, 2, 2)),
			)
		}
	}
}