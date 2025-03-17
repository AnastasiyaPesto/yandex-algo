package ru.zentsova.yandex.sprint5.finalka

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AktTest {

	@Test
	fun add() {
//		fun comparator() =
//			compareBy<Intern> { it.taskCount }.thenByDescending { it.penaltyCount }.thenByDescending { it.login }

		fun comparator() =
			compareByDescending<Intern> { it.taskCount }.thenBy { it.penaltyCount }.thenBy { it.login }

		val alla = Intern("alla", 4, 100)
		val gena = Intern("gena", 6, 1000)
		val gosha = Intern("gosha", 2, 90)
		val rita = Intern("rita", 2, 90)
		val timofey = Intern("timofey", 4, 80)

    val array = arrayOf(alla, gena, gosha, rita, timofey)

		heapSort(array, comparator())

		assertThat(array).isEqualTo(arrayOf(gena, timofey, alla, gosha, rita))
	}

	@Test
	fun add1() {
		fun comparator() =
			compareByDescending<Intern> { it.taskCount }.thenBy { it.penaltyCount }.thenBy { it.login }

		val alla = Intern("alla", 0, 0)
		val gena = Intern("gena", 0, 0)
		val gosha = Intern("gosha", 0, 0)
		val rita = Intern("rita", 0, 0)
		val timofey = Intern("timofey", 0, 0)

		val array = arrayOf(alla, gena, gosha, rita, timofey)

		heapSort(array, comparator())

		assertThat(array).isEqualTo(arrayOf(alla, gena, gosha, rita, timofey))
	}

	@Test
	fun comparatorTest() {
		fun comparatorInt() = compareBy<Int> { it }
		assertThat(comparatorInt().compare(6, 4)).isGreaterThan(0)

		fun comparatorString() = compareBy<String> { it }
		assertThat(comparatorString().compare("a", "a")).isEqualTo(0)
		assertThat(comparatorString().compare("abb", "alla")).isLessThan(0)
		assertThat(comparatorString().compare("aab", "aa")).isGreaterThan(0)
		assertThat(comparatorString().compare("aaa", "aa")).isGreaterThan(0)

		assertThat(comparatorString().compare("alla", "gena")).isLessThan(0)
		assertThat(comparatorString().compare("gena", "gosha")).isLessThan(0)
		assertThat(comparatorString().compare("gosha", "rita")).isLessThan(0)
		assertThat(comparatorString().compare("rita", "timofey")).isLessThan(0)
		assertThat(comparatorString().compare("rita", "gosha")).isGreaterThan(0)
	}
}