package com.thoughtworks.kotlin_basic.util

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class ColumnNameUtilTest {

    @Test
    fun `(1, 5) should return (A-E)`() {
        val columnNameUtil = ColumnNameUtil()

        assertEquals(listOf("A", "B", "C", "D", "E"), columnNameUtil.getRange(1, 5))
    }

    @Test
    fun `(2, 5) should return (B-E)`() {
        val columnNameUtil = ColumnNameUtil()

        assertEquals(listOf("B", "C", "D", "E", "F"), columnNameUtil.getRange(2, 5))
    }

    @Test
    fun `(1, 28) should return (A-AB)`() {
        val columnNameUtil = ColumnNameUtil()

        val columnNames = columnNameUtil.getRange(1, 28)

        assertEquals(28, columnNames.size)
        assertEquals("A", columnNames[0])
        assertEquals("AB", columnNames[columnNames.size - 1])
    }

    @Test
    fun `(26, 3) should return (Z-AB)`() {
        val columnNameUtil = ColumnNameUtil()

        val columnNames = columnNameUtil.getRange(26, 3)

        assertEquals(listOf("Z", "AA", "AB"), columnNames)
    }

    @Test
    fun `should throw exception when arguments is illegal`() {
        val columnNameUtil = ColumnNameUtil()

        assertThrows<IllegalArgumentException> { columnNameUtil.getRange(0, 1) }
        assertThrows<IllegalArgumentException> { columnNameUtil.getRange(1, 0) }
        assertThrows<IllegalArgumentException> { columnNameUtil.getRange(1, 1000000) }
    }
}
