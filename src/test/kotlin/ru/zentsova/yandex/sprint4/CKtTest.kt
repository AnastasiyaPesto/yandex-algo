package ru.zentsova.yandex.sprint4

import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.ParameterizedTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import java.util.stream.Stream

class CKtTest {

  @ParameterizedTest
	@MethodSource("stringsProvider")
	fun compare(string1: String, string2: String, expected: Boolean) {
		val actual = compare(string1, string2)
		assertThat(actual).isEqualTo(expected)
	}

	companion object {
		@JvmStatic
		fun stringsProvider(): Stream<Arguments> {
			return Stream.of(
				arguments("mxyskaoghi", "qodfrgmslc", true),
				arguments("agg", "xdd", true),
				arguments("agg", "xda", false),
				arguments("aaaaaaa", "bbbbbbb", true),
				arguments("aa", "b", false),
				arguments("a", "bb", false),
				arguments("aaaaaaa", "bbbbbb", false),
				arguments("z", "y", true),
				arguments("abcdee", "abcder", false),
				arguments("aba", "xxx", false),
				arguments("abacaba", "abacabac", false),
			)
		}
	}
}