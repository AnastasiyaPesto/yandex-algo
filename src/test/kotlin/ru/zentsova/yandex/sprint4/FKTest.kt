package ru.zentsova.yandex.sprint4

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FKTest {

	@Test
	fun test() {
		val prefixHashes = PrefixHashes(string = "cqvkvmzcab", a = 939, m = 9538360)

		assertThat(prefixHashes.hashCode(1, 3)).isEqualTo(4361782)
		assertThat(prefixHashes.hashCode(6, 7)).isEqualTo(114657)
		assertThat(prefixHashes.hashCode(1, 6)).isEqualTo(8617049)
		assertThat(prefixHashes.hashCode(2, 8)).isEqualTo(5919440)
	}
}