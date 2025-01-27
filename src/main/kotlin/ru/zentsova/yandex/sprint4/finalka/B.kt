package ru.zentsova.yandex.sprint4.finalka

import java.io.BufferedReader
import java.util.*
import kotlin.math.abs

class HashTable(private val size: Int = 99991) {
	private val table = Array<MutableList<Pair1>?>(size) { null }

	fun put(key: Int, value: Int) {
		val hashCode = hashCode(key)
		val idx = mod(hashCode, size)
		val list = table[idx]

		var shouldAdd = true
		if (list != null) {
			for (i in list.indices) {
				if (list[i].key == key) {
					list[i].value = value
					shouldAdd = false
					break
				}
			}
			if (shouldAdd) table[idx]!!.add(Pair1(key, value))
		} else {
			table[idx] = mutableListOf(Pair1(key, value))
		}
	}

	fun get(key: Int): Int? {
		val hashCode = hashCode(key)
		val idx = mod(hashCode, size)
		val list = table[idx]

		return if (list != null) {
			var value: Int? = null
			for (i in list.indices) {
				if (list[i].key == key) {
					value = list[i].value
					break
				}
			}
			value
		} else null
	}

	fun delete(key: Int): Int? {
		val hashCode = hashCode(key)
		val idx = mod(hashCode, size)
		val list = table[idx]

		return if (list != null) {
			var idxToDelete = -1
			for (i in list.indices) {
				if (list[i].key == key) {
					idxToDelete = i
					break
				}
			}

			val valueToReturn = if (idxToDelete != -1) {
				val value = list[idxToDelete].value
				list.removeAt(idxToDelete)
				value
			} else null

			valueToReturn
		} else null
	}

	private fun hashCode(k: Int): Int {
		return k
	}

	private fun mod(x: Int, y: Int): Int {
		val j = when {
			x < 0 -> -1 * (abs(x) / y + 1)
			else -> x / y
		}
		return x - j
	}

	private data class Pair1(
		val key: Int,
		var value: Int,
	)
}

fun main() {
	val reader = System.`in`.bufferedReader()
  val operationsCount = reader.readInt()
	val hashTable = HashTable()
	for (i in 0 until operationsCount) {
		val (operation, key, value) = reader.command()
		when (operation) {
			Operation.PUT -> hashTable.put(key, value!!)
			Operation.GET -> println(hashTable.get(key) ?: "None")
			Operation.DELETE -> println(hashTable.delete(key) ?: "None")
		}
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
	"put" -> Operation.PUT
	"get" -> Operation.GET
	"delete" -> Operation.DELETE
	else -> throw IllegalArgumentException("Unknown operation")
}

enum class Operation {
	PUT, GET, DELETE,
}
