package ru.zentsova.yandex.sprint4

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EKtTest {

	@Test
	fun generateEqualByHashWords() {
		val q = 1000
		val r = 123987123

		val alphabet = "abcdefghijklmnopqrstuvwxyz"
		val (firstWord, secondWord) = generateEqualByHashWords(source = alphabet, length = 10)
		val firstWordHash = polynomialHash(firstWord, q, r)
		val secondWordHash = polynomialHash(secondWord, q, r)

		assertThat(firstWord).isEqualTo(firstWord)
		assertThat(firstWordHash).isEqualTo(secondWordHash)

		println(firstWord)
		println(secondWord)
	}
}