package ru.zentsova.yandex.sprint3

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class PKtTest {


	@ParameterizedTest
	@MethodSource("arrayProvider")
	fun parts(array: IntArray, expected: Int) {
		val actual = parts(array)
		assertThat(actual).isEqualTo(expected)
	}

	companion object {

		@JvmStatic
		fun arrayProvider(): Stream<Arguments> = Stream.of(
			arguments(intArrayOf(0, 1, 3, 2), 3),
			arguments(intArrayOf(3, 6, 7, 4, 1, 5, 0, 2), 1),
			arguments(intArrayOf(1, 0, 2, 3, 4), 4),
		)
	}
}