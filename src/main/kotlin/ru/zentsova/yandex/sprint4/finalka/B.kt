package ru.zentsova.yandex.sprint4.finalka

/*
-- Спринт 4. Финалка. B. Хеш-таблица --
Ссылка на удачную посылку: https://contest.yandex.ru/contest/24414/run-report/132440959/,
  после 1-го ревью: https://contest.yandex.ru/contest/24414/run-report/132963835/,
  после 2-го ревью: https://contest.yandex.ru/contest/24414/run-report/133033851/

-- ПРИНЦИП РАБОТЫ --
Так как в условии сказано, что поддерживать рехеширование и масштабирование хеш-таблицы не требуется,
то в качестве размера массива я сразу выбрала первое ближайшее простое число
к удвоенному заданному ограничению (10^5) = 200003
И размер у меня не изменяется.

В качестве метода для разрешения коллизий я выбрала - метод цепочек.

Функция хеширования возвращает число, переданное на вход.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Для всех методов вычисление индекса в массиве происходит одинаково:
1. Вычисляется хеш-код от ключа
2. Значение из п.1 деление по модулю на размер массива

Метод PUT:
- Если по вычисленному индексу связанный список не пустой, то в цикле сравниваются элементы по ключу.
  Если ключ не найден, то в конец связанного списка кладется значение (key, value).
  Если по ключу существует запись, то обновляется у текущей записи значение value.
- Иначе, создается связанный список из одного элемента (key, value).

Метод GET:
- Как и в методе PUT ищется по ключу элемент, который необходимо вернуть,
  если найден, то возвращается value (ключи сравниваются по equals()), иначе возвращаем null.

Метод DELETE:
- Как и в методе PUT ищется по ключу элемент, который необходимо удалить.
  Если элемент найден, возвращается value и записывается null в ячейку, иначе возвращается null.


-- ВРЕМЕННАЯ СЛОЖНОСТЬ --

В среднем O(1)
В худшем случае O(N), где N размер списка в бакете.
Иногда из-за неизбежных коллизий по ключу в бакете лежит связанный список и его необходимо обойти, чтобы найти элемент по ключу.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --

В худшем случае O(N), где N - размер массива в котором хранятся элементы.
*/

import java.io.BufferedReader
import java.util.LinkedList

fun main() {
	val reader = System.`in`.bufferedReader()
	val operationsCount = reader.readInt()
	val hashTable = HashTable()
	val outputBuffer = buildString {
		for (i in 0 until operationsCount) {
			val (operation, key, value) = reader.command()
			when (operation) {
				Operation.PUT -> hashTable.put(key, value!!)
				Operation.GET -> appendLine(hashTable.get(key) ?: "None")
				Operation.DELETE -> appendLine(hashTable.delete(key) ?: "None")
			}
		}
	}

	println(outputBuffer)
}

private fun BufferedReader.readInt() = readLine().toInt()

private fun BufferedReader.command(): Triple<Operation, Int, Int?> {
	val inputs = readLine().split(" ")

	val operation = Operation.fromString(inputs.first())
	val key = inputs[1].toInt()
	val value = inputs.getOrNull(2)?.toInt()

	return Triple(operation, key, value)
}

class HashTable {
	private val size: Int = 200003
	private val table = Array<LinkedList<KeyValue>>(size) { LinkedList() }

	fun put(key: Int, value: Int) {
		val idx = bucketIndex(key)

		table[idx]
			.firstOrNull { it.key == key }
			?.let { it.value = value }
			?: table[idx].add(KeyValue(key, value))
	}

	fun get(key: Int): Int? {
		val idx = bucketIndex(key)
		return table[idx].firstOrNull { it.key == key }?.value
	}

	fun delete(key: Int): Int? {
		val idx = bucketIndex(key)
		val iterator = table[idx].iterator()

		while (iterator.hasNext()) {
			val entry = iterator.next()
			if (entry.key == key) {
				iterator.remove()
				return entry.value
			}
		}
		return null
	}

	private fun hashCode(key: Int) = key

	private fun bucketIndex(key: Int) = hashCode(key).mod(size)

	private data class KeyValue(
		val key: Int,
		var value: Int,
	)
}

enum class Operation {
	PUT, GET, DELETE;

	companion object {
		private val map = Operation.values().associateBy { it.name.lowercase() }

		fun fromString(value: String): Operation =
			map[value] ?: throw IllegalArgumentException("Unknown operation: $value")
	}
}
