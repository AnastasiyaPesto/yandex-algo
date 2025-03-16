package ru.zentsova.yandex.sprint5.finalka

/*
-- Спринт 5. Финалка. A. Пирамидальная сортировка --
Ссылка на удачную посылку: https://contest.yandex.ru/contest/24810/run-report/134973138/

-- ПРИНЦИП РАБОТЫ --
Пирамидальная сортировка осуществляется с использованием бинарной кучи (пирамиды).
Пирамида (binary heap) — это структура данных, представляющая собой объект-массив, который можно рассматривать как почти полное бинарное дерево.
По факту это массив, где нет пробелов между елементами (кроме элемента с индексом 0). По этому индексу в моём варианте хранится null.
Так же соблюдаются условия A[parent(i)] >= A[i] для max-heap и A[parent(i)] <= A[i] для min-heap.

Краткое описание алгоритма:
1. Из входящих данных формируем бинарную кучу (max-heap). Каждый новый элемент добавляется в конец и "просеивается вверх".
2. Когда куча полностью сформирована, начинаем по одному извлекать элемент по индексу с номером 1.
3. Так как по индексу 1 теперь пустое значение, то его нужно заполнить. Берем крайний правый элемент и помещаем на вершины (индекс 1).
Этот элемент "просеиваем вниз".

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
В бинарной куче на максимум на вершине (индекс 1) всегда лежит наибольший элемент в текущий момент времени.
Поэтому, извлекая на каждом шаге элемент из вершины, можно быть уверенными, что каждый последующий элемент будет не больше, чем предыдущий.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
O(1) - создание бинарной кучи
O(NlogN) - вставка элемента в бинарную кучу (вставка в конец + просеивание вверх)
O(NlogN) - извлечение элемента из вершины (извлечение + просеивание вниз элемента, который пришел на замену извлеченного)
O(1) + O(NlogN) + O(NlogN) = O(NlogN)

n - кол-во элементов

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(n) - хранение бинарной кучи (массив)

n - кол-во элементов
*/

import java.io.BufferedReader

fun main() {
  val reader = System.`in`.bufferedReader()
  val internsCount = reader.readInt()
  val internsMaxHeap = Heap<Intern>()
  repeat(internsCount) {
    internsMaxHeap.add(reader.intern, comparator())
  }
  while (internsMaxHeap.size > 1) {
    println(internsMaxHeap.pop(comparator())!!.login)
  }
}

private fun BufferedReader.readInt() = readLine().toInt()

private val BufferedReader.intern: Intern
  get() {
    val (login, tasksCount, penaltiesCount) = readLine().split(" ")
    return Intern(
      login = login,
      taskCount = tasksCount.toInt(),
      penaltyCount = penaltiesCount.toInt()
    )
  }

data class Intern(
  val login: String,
  val taskCount: Int,
  val penaltyCount: Int,
)

private fun comparator() =
  compareBy<Intern> { it.taskCount }.thenByDescending { it.penaltyCount }.thenByDescending { it.login }

class Heap<T : Any> {
  private val maxValueIdx = 1
  val heap: MutableList<T?> = mutableListOf(null)
  val size get() = heap.size

  fun add(value: T, comparator: Comparator<in T>) {
    heap.add(value)
    siftUp(heap.lastIndex, comparator)
  }

  fun pop(comparator: Comparator<in T>): T? = when {
    size == 2 -> heap.removeAt(heap.lastIndex)
    size > 2 -> {
      val maxValue = heap[maxValueIdx]
      heap[maxValueIdx] = heap.removeAt(heap.lastIndex)
      siftDown(maxValueIdx, comparator)
      maxValue
    }

    else -> null
  }

  private fun siftUp(idx: Int, comparator: Comparator<in T>) {
    if (idx == 1 || comparator.compare(heap[idx / 2], heap[idx]) >= 0) return

    heap.swap(idx, idx / 2)
    siftUp(idx / 2, comparator)
  }

  private fun siftDown(idx: Int, comparator: Comparator<in T>) {
    val left = 2 * idx
    val right = left + 1

    if (left >= size) return

    val largestIdx = if (right < size && comparator.compare(heap[right], heap[left]) > 0) right else left

    if (comparator.compare(heap[largestIdx], heap[idx]) > 0) {
      heap.swap(idx, largestIdx)
      siftDown(largestIdx, comparator)
    }
  }

  private fun MutableList<T?>.swap(from: Int, to: Int) {
    val tmp = this[from]
    this[from] = this[to]
    this[to] = tmp
  }
}