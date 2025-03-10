package ru.zentsova.yandex.sprint5

// M. Просеивание вверх

fun siftUp(heap: IntArray, idx: Int): Int {
	if (idx == 1 || heap[idx] <= heap[idx / 2]) return idx

	heap.swap(idx, idx / 2)
	return siftUp(heap, idx / 2)
}

fun siftUpLoop(heap: IntArray, idx: Int): Int {
	var i = idx
	while (i > 1 && heap[i] > heap[i / 2]) {
		heap.swap(i, i / 2)
		i /= 2
	}
	return i
}

private fun IntArray.swap(left: Int, right: Int) {
	val tmp = this[left]
	this[left] = this[right]
	this[right] = tmp
}

fun test() {
	val sample = intArrayOf(-1, 12, 6, 8, 3, 15, 7)
	assert(siftUp(sample, 5) == 1)
}