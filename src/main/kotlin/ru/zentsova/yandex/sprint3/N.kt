package ru.zentsova.yandex.sprint3

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
	val reader = BufferedReader(InputStreamReader(System.`in`))
	val n = reader.readLine().toInt()
	val sections = mutableListOf<Interval>()
	for (i in 0 until n) {
		val section = reader.readLine().split(" ").map { it.toInt() }
		sections.add(Interval(section.first(), section.last()))
	}

	flowerBeds(sections)
		.forEach {
		println("${it.start} ${it.end}")
	}
}

fun flowerBeds(intervals: List<Interval>): List<Interval> {
	val intervalsSorted = intervals.sortedBy { it.end }

	var result = intervalsSorted to true
	do {
		result = checkIntervals(result.first, result.second)
	} while (result.second)

	return result.first
}

fun checkIntervals(intervals: List<Interval>, checkAgain: Boolean): Pair<List<Interval>, Boolean> {
	if (intervals.size == 1 || !checkAgain) return intervals to false

	var newCheckAgain = false
	val flowerBeds = mutableListOf<Interval>()
	for ((start, end) in intervals ) {
		if (flowerBeds.isEmpty() || start > flowerBeds.last().end) {
			flowerBeds.add(Interval(start, end))
		} else {
			newCheckAgain = true
			if (end > flowerBeds.last().end) {
				flowerBeds.last().end = end
			}
			if (start < flowerBeds.last().start) {
				flowerBeds.last().start = start
			}
		}
	}

	return flowerBeds to newCheckAgain
}

data class Interval(
	var start: Int,
	var end: Int
)