package ru.zentsova.yandex.sprint5

// L. Просеивание вниз

fun siftDown(heap: IntArray, idx: Int): Int {
	val left = 2 * idx
	val right = left + 1

	if (left >= heap.size) return idx

	val largestIdx = if (right < heap.size && heap[right] > heap[left]) right else left

	return if (heap[idx] < heap[largestIdx]) {
		heap.swap(idx, largestIdx)
		siftDown(heap, largestIdx)
	} else {
		idx
	}
}

private fun IntArray.swap(left: Int, right: Int) {
	val tmp = this[left]
	this[left] = this[right]
	this[right] = tmp
}

fun test() {
	val sample = intArrayOf(-1, 12, 1, 8, 3, 4, 7)
	assert(siftDown(sample, 2) == 5)
}