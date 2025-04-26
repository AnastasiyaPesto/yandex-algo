package ru.zentsova.yandex.sprint7

// B. Расписание
fun main() {
	val lessonCount = readInt()
	val lessons = List(lessonCount) {
		val (start, finish) = readDoubles()
		Lesson(start, finish)
	}

	val scheduleLessons = scheduleLessons(lessons)
	println(scheduleLessons.size)
	scheduleLessons.forEach {
		println("""${formatTime(it.start)} ${formatTime(it.finish)}""")
	}
}

fun scheduleLessons(lessons: List<Lesson>): List<Lesson> {
	// Сортировка по времени окончания
	val sorted = lessons.sortedWith(compareBy({ it.finish }, { it.start }))
	val result = mutableListOf<Lesson>()

	var lastEndTime = Double.NEGATIVE_INFINITY
	for (lesson in sorted) {
		if (lesson.start >= lastEndTime) {
			result.add(lesson)
			lastEndTime = lesson.finish
		}
	}

	return result
}

fun formatTime(time: Double): String =
	if (time % 1.0 == 0.0) {
		time.toInt().toString()
	} else {
		time.toString()
	}

private fun readInt() = readln().toInt()
private fun readDoubles() = readln().split(" ").map { it.toDouble() }

data class Lesson(val start: Double, val finish: Double)