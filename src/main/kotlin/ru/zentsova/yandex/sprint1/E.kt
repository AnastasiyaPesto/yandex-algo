package ru.zentsova.yandex.sprint1

fun main() {
    readln()
    val line = readln()
    val biggestWord = calcBiggestWord(line)
    println(biggestWord)
    println(biggestWord.length)
}

private fun calcBiggestWord(line: String): String {
    val collection = line.split(" ")
    var word = ""
    collection.forEach {
        if (it.length > word.length) {
            word = it
        }
    }
    return word
}