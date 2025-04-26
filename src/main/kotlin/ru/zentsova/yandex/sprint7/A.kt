package ru.zentsova.yandex.sprint7

// A. Биржа
fun main() {
  readInt()
	val stockPricesByDay = readInts()
	println(maxProfit(stockPricesByDay))
}

fun maxProfit(stockPricesByDay: List<Int>): Int {
	var profit = 0
	for (i in 0 until stockPricesByDay.size -1) {
		if (stockPricesByDay[i + 1] > stockPricesByDay[i]) {
			profit += stockPricesByDay[i + 1] - stockPricesByDay[i]
		}
	}
	return profit
}

private fun readInt() = readln().toInt()
private fun readInts() = readln().split(" ").map { it.toInt() }
