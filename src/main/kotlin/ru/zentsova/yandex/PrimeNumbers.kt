package ru.zentsova.yandex

fun main() {
	val n = readInt()
	println(isPrime(n))
}

fun isPrime(n: Int): Boolean {
	if (n == 1) return false
	var i = 2
	while (i < n) {
		if (n % i == 0) return false
		i++
	}
	return true
}

fun isPrimeEratosthenes(n: Int): BooleanArray {
	val numbers = BooleanArray(n + 1) { true }
	numbers[0] = false
	numbers[1] = false
	for (num in 2..n) {
		if (numbers[num]) {
			for (j in (num * num)..n step num) {
				numbers[j] = false
			}
		}
	}
	return numbers
}

private fun readStr() = readln()

private fun readInt() = readStr().toInt()