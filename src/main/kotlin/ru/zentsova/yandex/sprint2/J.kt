package ru.zentsova.yandex.sprint2

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class MyQueueList {

	private val queue = LinkedList<Int>()

	fun put(value: Int) {
		queue.addLast(value)
	}

	fun get(): Int? = try {
			queue.removeFirst()
		} catch (e: NoSuchElementException) {
			null
		}

	fun size() = queue.size
}

fun main() {
	val reader = BufferedReader(InputStreamReader(System.`in`))
	val commandCount = reader.readLine().toInt()
	val queue = MyQueueList()
	for (i in 0 until commandCount) {
		val token = StringTokenizer(reader.readLine())
		val commandName = token.nextToken()
		when (commandName) {
			"put" -> queue.put(token.nextToken().toInt())
			"get" -> println(queue.get() ?: "error")
			"size" -> println(queue.size())
		}
	}
}