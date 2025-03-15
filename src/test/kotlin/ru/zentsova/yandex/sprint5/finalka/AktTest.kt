package ru.zentsova.yandex.sprint5.finalka

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AktTest {
	private val internsMaxHeap = Heap<Intern>()

	@Test
	fun add() {
		fun comparator() =
			compareBy<Intern> { it.taskCount }.thenByDescending { it.penaltyCount }.thenByDescending { it.login }

		val alla = Intern("alla", 4, 100)
		val gena = Intern("gena", 6, 1000)
		val gosha = Intern("gosha", 2, 90)
		val rita = Intern("rita", 2, 90)
		val timofey = Intern("timofey", 4, 80)

		internsMaxHeap.add(alla, comparator())
		internsMaxHeap.add(gena, comparator())
		internsMaxHeap.add(gosha, comparator())
		internsMaxHeap.add(rita, comparator())
		internsMaxHeap.add(timofey, comparator())

		var actual = internsMaxHeap.heap.mapNotNull { it }.map { it.login }
		assertThat(actual).isEqualTo(listOf(gena.login, timofey.login, gosha.login, rita.login, alla.login))

		var intern = internsMaxHeap.pop(comparator())
		actual = internsMaxHeap.heap.mapNotNull { it }.map { it.login }
		assertThat(intern).isEqualTo(gena)
		assertThat(actual).isEqualTo(listOf(timofey.login, alla.login, gosha.login, rita.login))

		intern = internsMaxHeap.pop(comparator())
		actual = internsMaxHeap.heap.mapNotNull { it }.map { it.login }
		assertThat(intern).isEqualTo(timofey)
		assertThat(actual).isEqualTo(listOf(alla.login, rita.login, gosha.login))

		intern = internsMaxHeap.pop(comparator())
		actual = internsMaxHeap.heap.mapNotNull { it }.map { it.login }
		assertThat(intern).isEqualTo(alla)
		assertThat(actual).isEqualTo(listOf(gosha.login, rita.login))

		intern = internsMaxHeap.pop(comparator())
		actual = internsMaxHeap.heap.mapNotNull { it }.map { it.login }
		assertThat(intern).isEqualTo(gosha)
		assertThat(actual).isEqualTo(listOf(rita.login))

		intern = internsMaxHeap.pop(comparator())
		actual = internsMaxHeap.heap.mapNotNull { it }.map { it.login }
		assertThat(intern).isEqualTo(rita)
		assertThat(actual).isEmpty()

		intern = internsMaxHeap.pop(comparator())
		actual = internsMaxHeap.heap.mapNotNull { it }.map { it.login }
		assertThat(intern).isNull()
		assertThat(actual).isEmpty()
	}

	@Test
	fun add1() {
		fun comparator() =
			compareBy<Intern> { it.taskCount }.thenByDescending { it.penaltyCount }.thenByDescending { it.login }

		val alla = Intern("alla", 0, 0)
		val gena = Intern("gena", 0, 0)
		val gosha = Intern("gosha", 0, 0)
		val rita = Intern("rita", 0, 0)
		val timofey = Intern("timofey", 0, 0)

		internsMaxHeap.add(alla, comparator())
		internsMaxHeap.add(gena, comparator())
		internsMaxHeap.add(gosha, comparator())
		internsMaxHeap.add(rita, comparator())
		internsMaxHeap.add(timofey, comparator())

		var actual = internsMaxHeap.heap.mapNotNull { it }.map { it.login }
//		assertThat(actual).isEqualTo(listOf(gena.login, timofey.login, gosha.login, rita.login, alla.login))

		var intern = internsMaxHeap.pop(comparator())
		actual = internsMaxHeap.heap.mapNotNull { it }.map { it.login }
		assertThat(intern).isEqualTo(alla)
//		assertThat(actual).isEqualTo(listOf(timofey.login, alla.login, gosha.login, rita.login))

		intern = internsMaxHeap.pop(comparator())
		actual = internsMaxHeap.heap.mapNotNull { it }.map { it.login }
		assertThat(intern).isEqualTo(gena)
//		assertThat(actual).isEqualTo(listOf(alla.login, rita.login, gosha.login))

		intern = internsMaxHeap.pop(comparator())
		actual = internsMaxHeap.heap.mapNotNull { it }.map { it.login }
		assertThat(intern).isEqualTo(gosha)
//		assertThat(actual).isEqualTo(listOf(gosha.login, rita.login))

		intern = internsMaxHeap.pop(comparator())
		actual = internsMaxHeap.heap.mapNotNull { it }.map { it.login }
		assertThat(intern).isEqualTo(rita)
//		assertThat(actual).isEqualTo(listOf(rita.login))

		intern = internsMaxHeap.pop(comparator())
		actual = internsMaxHeap.heap.mapNotNull { it }.map { it.login }
		assertThat(intern).isEqualTo(timofey)
		assertThat(actual).isEmpty()

		intern = internsMaxHeap.pop(comparator())
		actual = internsMaxHeap.heap.mapNotNull { it }.map { it.login }
		assertThat(intern).isNull()
		assertThat(actual).isEmpty()
	}

	@Test
	fun comparatorTest() {
		fun comparator1() = compareBy<Int> { it }

		assertThat(comparator1().compare(6, 4)).isGreaterThan(0)
	}
}