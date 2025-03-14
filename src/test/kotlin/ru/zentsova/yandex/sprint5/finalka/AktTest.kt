package ru.zentsova.yandex.sprint5.finalka

import org.junit.jupiter.api.Test

class AktTest {
	private val internsMaxHeap = Heap<Intern>()
	private fun comparator() =
		compareByDescending<Intern> { it.taskCount }.thenBy { it.penaltyCount }.thenBy { it.login }

	@Test
	fun add() {
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

		println(internsMaxHeap.heap.filterNotNull().joinToString(separator = "\n") { it.login })
	}
}