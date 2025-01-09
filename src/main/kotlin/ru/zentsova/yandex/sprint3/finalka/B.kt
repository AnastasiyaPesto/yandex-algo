package ru.zentsova.yandex.sprint3.finalka

import java.io.BufferedReader
import java.io.InputStreamReader

data class Intern(
	val login: String,
	val taskCount: Int,
	val penaltyCount: Int,
)

fun main() {
	val reader = BufferedReader(InputStreamReader(System.`in`))
	val internNums = reader.readInt()
	val interns = reader.readInterns(internNums)
}

private fun BufferedReader.readInterns(n: Int) = (0 until n).map {
	val (login, tasksCount, penaltiesCount) = readLine().split(" ")
	Intern(
		login = login,
		taskCount = tasksCount.toInt(),
		penaltyCount = penaltiesCount.toInt(),
	)
}

private fun quickSortInPlace(interns: MutableList<Intern>, left: Int, right: Int) {

}

private fun BufferedReader.readInt() = readLine().toInt()