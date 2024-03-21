package com.thoughtworks.kotlin_basic.api

import com.thoughtworks.kotlin_basic.domain.ProductType
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class ApiClientTest {

    @Test
    fun `query products success`() = runBlocking {
        val products = ApiClient.productApiService.queryProducts()

        assertEquals(5, products.size)
        assertEquals("1", products[0].id)
        assertEquals("ABC123", products[0].sku)
        assertEquals("Electronic Watch", products[0].name)
        assertEquals(BigDecimal("299.99"), products[0].price)
        assertEquals(ProductType.NORMAL, products[0].type)
        assertEquals("image1.jpg", products[0].image)
    }

    @Test
    fun `query inventories success`() = runBlocking {
        val inventories = ApiClient.productApiService.queryInventories()

        assertEquals(8, inventories.size)
        assertEquals("1", inventories[0].id)
        assertEquals("ABC123", inventories[0].sku)
        assertEquals("CN_NORTH", inventories[0].zone)
        assertEquals(120, inventories[0].quantity)

    }

}