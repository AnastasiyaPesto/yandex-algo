package ru.zentsova.yandex.sprint2.finalka

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

internal class Dequeue(private val capacity: Int) {
	private val dequeue: Array<Int?> = arrayOfNulls(capacity)
	private var head = 0
	private var tail = 0
	private var size = 0
	private val isEmpty: Boolean
		get() = size == 0

	fun pushBack(x: Int?): Boolean {
		if (size == capacity) return false
		tail = (tail - 1 + capacity) % capacity
		dequeue[tail] = x
		size++
		return true
	}

	fun popBack(): Int? {
		if (isEmpty) {
			return null
		}
		val x = dequeue[tail]
		dequeue[tail] = null
		tail = (tail + 1) % capacity
		size--
		return x
	}

	fun pushFront(x: Int?): Boolean {
		if (size == capacity) return false
		dequeue[head] = x
		head = (head + 1) % capacity
		size++
		return true
	}

	fun popFront(): Int? {
		if (isEmpty) {
			return null
		}
		head = (head - 1 + capacity) % capacity
		val x = dequeue[head]
		dequeue[head] = null
		size--
		return x
	}
}

fun main() {
	val reader = BufferedReader(InputStreamReader(System.`in`))
	val commandCount = reader.readInt()
	val dequeueMaxSize = reader.readInt()
	val dequeue = Dequeue(dequeueMaxSize)
	val outputBuffer = StringBuilder()
	for (i in 0 until commandCount) {
		val tokenizer = StringTokenizer(reader.readLine())
		val commandName = tokenizer.nextToken()
		var elem = 0
		if (commandName == "push_front" || commandName == "push_back") elem = tokenizer.nextToken().toInt()
		when (commandName) {
			"push_front" -> pushFront(dequeue, elem, outputBuffer)
			"push_back" -> pushBack(dequeue, elem, outputBuffer)
			"pop_front" -> popFront(dequeue, outputBuffer)
			"pop_back" -> popBack(dequeue, outputBuffer)
		}
	}
	println(outputBuffer)
}

private fun BufferedReader.readInt() = this.readLine().toInt()


private fun pushFront(dequeue: Dequeue, elem: Int, outputBuffer: StringBuilder) {
	if (!dequeue.pushFront(elem)) {
		outputBuffer.append("error").append("\n")
	}
}

private fun pushBack(dequeue: Dequeue, elem: Int, outputBuffer: StringBuilder) {
	if (!dequeue.pushBack(elem)) {
		outputBuffer.append("error").append("\n")
	}
}

private fun popFront(dequeue: Dequeue, outputBuffer: StringBuilder) {
	outputBuffer.append(dequeue.popFront() ?: "error").append("\n")
}

private fun popBack(dequeue: Dequeue, outputBuffer: StringBuilder) {
	outputBuffer.append(dequeue.popBack() ?: "error").append("\n")
}