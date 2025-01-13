package ru.zentsova.yandex.sprint3.finalka

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class BKtTest {

	@ParameterizedTest
	@MethodSource("internsProvider")
	fun quickSortInPlace(interns: Array<Intern>, expected: Array<Intern>) {
		val comparator = compareByDescending<Intern> { it.taskCount }
			.thenBy { it.penaltyCount }
			.thenBy { it.login }
//		val comparator = compareBy<Intern> { it.taskCount }
//			.thenByDescending { it.penaltyCount }
//			.thenByDescending { it.login }
		interns.quickSortInPlace(0, interns.lastIndex, comparator)

		assertThat(interns.toList()).containsExactlyElementsOf(expected.toList())
	}

	companion object {

		@JvmStatic
		fun internsProvider(): Stream<Arguments> = Stream.of(
			arguments(
				arrayOf(
					Intern("alla", 4, 100),
					Intern("gena", 6, 1000),
					Intern("gosha", 2, 90),
					Intern("rita", 2, 90),
					Intern("timofey", 4, 80),
				),
				arrayOf(
					Intern("gena", 6, 1000),
					Intern("timofey", 4, 80),
					Intern("alla", 4, 100),
					Intern("gosha", 2, 90),
					Intern("rita", 2, 90),
				)
			),
			arguments(
				arrayOf(
					Intern("alla", 0, 0),
					Intern("gena", 0, 0),
					Intern("gosha", 0, 0),
					Intern("rita", 0, 0),
					Intern("timofey", 0, 0),
				),
				arrayOf(
					Intern("alla", 0, 0),
					Intern("gena", 0, 0),
					Intern("gosha", 0, 0),
					Intern("rita", 0, 0),
					Intern("timofey", 0, 0),
				),
			),
		)
	}
}