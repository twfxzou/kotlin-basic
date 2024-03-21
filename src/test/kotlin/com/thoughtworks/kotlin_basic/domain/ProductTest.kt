package com.thoughtworks.kotlin_basic.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.math.BigDecimal
import kotlin.test.assertEquals

class ProductTest {

    @Test
    fun `should get inventory total quantity`() {
        val product = Product(
            "1",
            "S001",
            "test",
            BigDecimal("20.1"),
            ProductType.NORMAL,
            "test.jpg",
            mutableListOf(
                Inventory("1", "S001", "CN", 100),
                Inventory("2", "S001", "US", 200)
            )
        )

        assertEquals(300, product.getInventoryQuantity())

    }

    @Test
    fun `should get origin price when product type is NORMAL`() {
        val product = Product(
            "1",
            "S001",
            "test",
            BigDecimal("20.1"),
            ProductType.NORMAL,
            "test.jpg",
            mutableListOf(
                Inventory("1", "S001", "CN", 1)
            )
        )

        assertEquals(BigDecimal("20.1"), product.getRealPrice())
    }

    @ParameterizedTest
    @CsvSource(
        "120, 20",
        "101, 20",
        "100, 24",
        "31, 24",
        "30, 30",
        "0, 30",
    )
    fun `should get origin price when product type is HIGH_DEMAND`(inventoryQuantity: Int, expectedPrice: BigDecimal) {
        val product = Product(
            "1",
            "S001",
            "test",
            BigDecimal("20"),
            ProductType.HIGH_DEMAND,
            "test.jpg",
            mutableListOf(
                Inventory("1", "S001", "CN", inventoryQuantity)
            )
        )

        assertEquals(expectedPrice.intValueExact(), product.getRealPrice().intValueExact())
    }
}