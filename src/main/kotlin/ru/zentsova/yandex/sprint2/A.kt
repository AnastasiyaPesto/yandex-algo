package ru.zentsova.yandex.sprint2

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
  val outputBuffer = StringBuilder()
  val reader = BufferedReader(InputStreamReader(System.`in`))
  val colCnt = reader.readInt()
  val rowCnt = reader.readInt()
  val res = Array(rowCnt) { IntArray(colCnt) }
  for (i in 0 until colCnt) {
    val line = StringTokenizer(reader.readLine())
    var row = 0
    while (row < rowCnt) {
      val num = line.nextToken().toInt()
      res[row++][i] = num
    }
  }

  for (i in 0 until rowCnt) {
    for (j in res[i].indices) {
      outputBuffer.append(res[i][j]).append(" ")
    }
    outputBuffer.append("\n")
  }
  println(outputBuffer)
}

private fun BufferedReader.readInt() = this.readLine().toInt()