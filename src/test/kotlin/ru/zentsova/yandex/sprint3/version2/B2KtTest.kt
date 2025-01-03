package ru.zentsova.yandex.sprint3.version2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class B2KtTest {
	private val dictionary = listOf("", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz")

	@Test
	fun allLettersCombinations() {
		val result = mutableListOf<String>()
		allLettersCombinations("23", dictionary, result, mutableListOf())
		assertThat(result).isEqualTo(listOf("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"))
	}
}