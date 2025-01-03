package ru.zentsova.yandex.sprint2

class Node<V>(var value: V, var next: Node<V>? = null, var prev: Node<V>? = null)

class Stack(var cur: Node<Char>? = null) {

  fun push(char: Char) {
    if (cur == null) {
      cur = Node(value = char)
    } else {
      cur = Node(value = char, prev = cur)
      cur!!.prev!!.next = cur
    }
  }

  fun pop(): Char? {
    if (cur == null) return null

    val ch = cur!!.value
    cur = cur!!.prev

    return ch
  }

  fun isEmpty(): Boolean {
    return cur == null
  }
}

val CLOSE_BRACKETS = setOf('}', ')', ']')
val CLOSE_OPEN_BRACKET_PAIRS = mapOf('}' to '{', ')' to '(', ']' to '[')

fun main() {
  val isCorrect = isCorrectBracketSeq(readln())
  if (isCorrect) println("True") else println("False")
}

private fun isCorrectBracketSeq(seq: String): Boolean {
  if (seq == null)
    return false

  if (seq.isEmpty())
    return true

  val stack = Stack()
  for (i in seq.indices) {
    val ch = seq[i]
    if (CLOSE_BRACKETS.contains(ch)) {
      val openBracket = CLOSE_OPEN_BRACKET_PAIRS[ch]
      val firstCh = stack.pop()
      if (firstCh == null || firstCh != openBracket) return false
    } else {
      stack.push(ch)
    }
  }

  return stack.isEmpty()
}