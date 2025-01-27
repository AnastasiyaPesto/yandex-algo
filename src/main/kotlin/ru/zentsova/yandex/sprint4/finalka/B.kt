package ru.zentsova.yandex.sprint4.finalka

import ru.zentsova.yandex.sprint4.finalka.Operation.PUT
import ru.zentsova.yandex.sprint4.finalka.Operation.GET
import ru.zentsova.yandex.sprint4.finalka.Operation.DELETE
import java.io.BufferedReader
import java.util.*

class HashTable<K, V>() {
	fun put(key: K, value: V) {

	}

	fun get(key: K): V? {

		return null
	}

	fun delete(k: K): V? {

		return null
	}

	private fun hashCode(k: K): Int {

		return 0
	}
}

fun main() {
	val reader = System.`in`.bufferedReader()
  val operationsCount = reader.readInt()
	val hashTable = HashTable<Int, Int>()
	for (i in 0..< operationsCount) {
		val (operation, key, value) = reader.command()
	}
}

private fun BufferedReader.readInt() = readLine().toInt()

private fun BufferedReader.command(): Triple<Operation, Int, Int?> {
	val tokenizer = StringTokenizer(readLine())

	val operation = tokenizer.nextToken().toOperation()
	val key = tokenizer.nextToken().toInt()
	val value = if (tokenizer.hasMoreTokens()) tokenizer.nextToken().toInt() else null

	return Triple(operation, key, value)
}

private fun String.toOperation(): Operation = when (this) {
	"put" -> PUT
	"get" -> GET
	"delete" -> DELETE
	else -> throw IllegalArgumentException("Unknown operation")
}

enum class Operation {
	PUT,
	GET,
	DELETE
}

