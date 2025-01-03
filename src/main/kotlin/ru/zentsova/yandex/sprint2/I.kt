package ru.zentsova.yandex.sprint2

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.RuntimeException
import java.util.*

class QueueFullSizeException : RuntimeException()

class MyQueueSized(private val maxSize: Int) {

	private val queue = arrayOfNulls<Int>(maxSize)
	private var head = 0
	private var tail = 0
	private var size = 0

	fun push(value: Int) {
		if (size == maxSize) throw QueueFullSizeException()

		queue[tail] = value
		tail = (tail + 1) % maxSize
		size++
	}

	fun pop(): Int? {
		if (size == 0) return null

		val first = queue[head]
		queue[head] = null
		head = (head + 1) % maxSize
		size--
		return first
	}

	fun peek(): Int? = if (size != 0) queue[head] else null

	fun size() = size
}

fun main() {
	val reader = BufferedReader(InputStreamReader(System.`in`))
	val commandCount = reader.readLine().toInt()
	val queue = MyQueueSized(reader.readLine().toInt())
	for (i in 0 until commandCount) {
		val token = StringTokenizer(reader.readLine())
		val commandName = token.nextToken()
		when (commandName) {
			"push" -> try {
				queue.push(token.nextToken().toInt())
			} catch (e: QueueFullSizeException) {
				println("error")
			}
			"pop" -> println(queue.pop() ?: "None")
			"peek" -> println(queue.peek() ?: "None")
			"size" -> println(queue.size())
		}
	}
}