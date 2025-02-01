package ru.zentsova.yandex.sprint4.finalka

import org.junit.jupiter.api.Test

class AKtTest {

  @Test
  fun documentRelevance() {
    val documents = listOf(
      countForSameWordsInLine("i love coffee"),
      countForSameWordsInLine("coffee with milk and sugar"),
      countForSameWordsInLine("free tea for everyone"),
    )

    val queries = listOf(
      "i like black coffee without milk",
      "everyone loves new year",
      "mary likes black coffee without milk",
    )

    val actual = documentRelevance2(
      queries = queries,
      documents = documents
    )
  }
}