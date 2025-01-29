package ru.zentsova.yandex.sprint4.finalka

/*
-- Спринт 4. Финалка. B. Хеш-таблица --
Ссылка на удачную посылку: https://contest.yandex.ru/contest/24414/run-report/132440959/


-- ПРИНЦИП РАБОТЫ --

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 */

import java.io.BufferedReader
import java.lang.Math.floorMod
import java.util.*

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

class HashTable {
	private val size: Int = 100003
	private val table = Array<MutableList<KeyValue>?>(size) { null }

	fun put(key: Int, value: Int) {
		val idx = bucketIndex(key)
		val list = table[idx]

		var shouldAdd = true
		if (list != null) {
			for (i in list.indices) {
				if (list[i].key == key) {
					shouldAdd = false
					break
				}
			}
			if (shouldAdd) table[idx]!!.add(KeyValue(key, value))
		} else {
			table[idx] = mutableListOf(KeyValue(key, value))
		}
	}

	fun get(key: Int): Int? {
		val idx = bucketIndex(key)
		val list = table[idx]

		return if (list != null) {
			for (i in list.indices) {
				if (list[i].key == key) {
					return list[i].value
				}
			}
			null
		} else null
	}

	fun delete(key: Int): Int? {
		val idx = bucketIndex(key)
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
		val prime = 31
		var result = k

		result = result xor (result ushr 16)
		result *= prime
		result = result xor (result ushr 13)
		result *= prime
		result = result xor (result ushr 14)

		return result
	}

	private fun bucketIndex(key: Int): Int {
		val hash = hashCode(key)
		return floorMod(hash, size)
	}

	private data class KeyValue(
		val key: Int,
		var value: Int,
	)
}

enum class Operation {
	PUT, GET, DELETE,
}
