package com.thoughtworks.kotlin_basic.util

import java.util.stream.Collectors
import java.util.stream.IntStream

class ColumnNameUtil {
    fun getRange(start: Int, count: Int): List<String> {
        if (start < 1 || count < 1) {
            throw IllegalArgumentException("illegal arguments")
        }
        var begin = start
        return IntStream.generate { begin++ }.boxed()
            .map(::convertToColumnName)
            .filter { it.isNotEmpty() }
            .peek { if (it > "ZZZ") throw IllegalArgumentException("Can't exceed 'ZZZ'") }
            .limit(count.toLong())
            .collect(Collectors.toList())
    }

    private fun convertToColumnName(index: Int): String {
        var i = index
        val result = mutableListOf<Int>()
        while (i > 0) {
            val current = i % 27
            if (current == 0) {
                return ""
            }
            result.add(current)
            i /= 27
        }
        return result.map { (it + 'A'.code - 1).toChar() }.reversed().joinToString("")
    }
}