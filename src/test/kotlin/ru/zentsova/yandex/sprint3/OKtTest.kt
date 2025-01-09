package ru.zentsova.yandex.sprint3

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class OKtTest {

	@ParameterizedTest
	@MethodSource("arrayProvider")
	fun trashIndex(array: IntArray, k: Long, expected: Int) {
		val actual = trashIndex(array, k)
		assertThat(actual).isEqualTo(expected	)
	}

	companion object {
		@JvmStatic
		fun arrayProvider(): Stream<Arguments> = Stream.of(
			arguments(intArrayOf(2, 3, 4), 2, 1),
			arguments(intArrayOf(1, 3, 1), 1, 0),
			arguments(intArrayOf(1, 3, 5), 3, 4),
		)
	}
}