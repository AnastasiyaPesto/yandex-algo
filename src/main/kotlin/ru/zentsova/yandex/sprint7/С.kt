package ru.zentsova.yandex.sprint7

data class GoldPile(val weight: Long, val pricePerKg: Long)

// C. Золотая лихорадка
fun main() {
	val capacity = readLong()
	val heapsNumber = readInt()
	val piles = Array(heapsNumber) {
		val (pricePerKg, weight) = readLongs()
		GoldPile(weight = weight, pricePerKg = pricePerKg)
	}
	println(getMaxValue(piles, capacity))
}

fun getMaxValue(piles: Array<GoldPile>, capacity: Long): Long {
	val sorted = piles.sortedByDescending { it.pricePerKg }
	var cap = capacity

	var i = 0
	var ans = 0L

	while (cap > 0 && i < sorted.size) {
		val min = minOf(cap, sorted[i].weight)
		ans += min * sorted[i].pricePerKg
		cap -= min
		i++
	}

	return ans
}

private fun readLong() = readln().toLong()
private fun readInt() = readln().toInt()
private fun readLongs() = readln().split(" ").map { it.toLong() }
