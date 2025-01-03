package ru.zentsova.yandex.sprint3.version2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class C2KtTest {
	@ParameterizedTest
	@MethodSource("stringsProvider")
	fun subsequence(firstLine: String, secondLine: String, result: Boolean) {
		assertThat(subsequence(firstLine, secondLine)).isEqualTo(result)
	}

	companion object {
		@JvmStatic
		fun stringsProvider(): Stream<Arguments> {
			return Stream.of(
				arguments("abc", "ahbgdcu", true),
				arguments("abc", "abc", true),
				arguments("abcp", "ahpc", false),
				arguments("qwerty", "qwe", false),
			)
		}
	}
}