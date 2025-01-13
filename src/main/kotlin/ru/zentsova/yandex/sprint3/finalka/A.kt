package ru.zentsova.yandex.sprint3.finalka

/*
-- Спринт 3. Финалка. А. Поиск в сломанном массиве --
Ссылка на удачную посылку: https://contest.yandex.ru/contest/23815/run-report/107304711/
Ссылка на удачную посылку (поле 1-ого ревью):
https://contest.yandex.ru/contest/23815/run-report/131371549/

-- ПРИНЦИП РАБОТЫ --
1. Так же как и в бинарном поиске, в первую очередь проверяем  значение по индексу mid
2. Сравниваем значение по индексу left и mid.
3. Если array[left] <= array[mid], то мы попали в промежуток, где все значения идут по возрастанию. Ищем в этом промежутке:
3.1. Если значение между, то двигаем правую границу
3.2. Если значение больше, чем array[mid], то переходим к поиску на другой половине
4. Если arr[left] > arr[mid], то нужно проанализировать промежуток от mid до right
4.1. Если значение k между, то двигаем левую границу
4.2. Если значение k больше, чем array[mid], то переходим к поиску на другой половине

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Из условия можно предположить, что на вход поступит либо отсортированный массив,
либо массив будет сдвинут и в нем будут отсортированы две части, идущие друг за другом.
Например, на промежутках [0;6) и [6;size) будут находиться два осортированных по возрастанию массива.
Причем array[5] > array[6], а array[0] > array[size - 1].
Отсюда думаю, что можно пользоваться бинарным поиском с соблюдением особенностей из условий.
То есть по итогу надо найти промежуток, на котором будем запускать бинарный поиск.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
O(logN), где N - размер входного массива - На каждой итерации массив делится на два

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(1) - так как храним исходный массив, ищем в нем же, и доп память не используем (кроме памяти для простых переменных)
 */

object Solution {
  fun brokenSearch(arr: IntArray, k: Int): Int {
    var left = 0
    var right = arr.lastIndex

    while (left <= right) {
      val mid = left + (right - left) / 2
      when {
        arr[mid] == k -> return mid
        arr[left] <= arr[mid] -> {
          if (k in arr[left]..arr[mid]) {
            right = mid - 1
          } else {
            left = mid + 1
          }
        }
        else -> {
          if (k in arr[mid]..arr[right]) {
            left = mid + 1
          } else {
            right = mid - 1
          }
        }
      }
    }

    return -1
  }
}