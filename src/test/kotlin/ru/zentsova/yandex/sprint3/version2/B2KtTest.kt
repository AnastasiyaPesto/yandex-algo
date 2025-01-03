package ru.zentsova.yandex.sprint3.version2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class B2KtTest {
	private val dictionary = listOf("", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz")

	@Test
	fun allLettersCombinations() {
		val expected = listOf("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf")
		val actual = mutableListOf<String>()
		allLettersCombinations("23", dictionary, actual, mutableListOf())
		assertThat(actual).isEqualTo(expected)
	}

	@Test
	fun combine() {
		val expected = listOf("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf")
		val actual = combine("23", dictionary)
		assertThat(actual).isEqualTo(expected)
	}
}