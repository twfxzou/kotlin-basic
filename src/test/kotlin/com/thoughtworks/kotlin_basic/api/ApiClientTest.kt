package com.thoughtworks.kotlin_basic.api

import com.thoughtworks.kotlin_basic.domain.ProductType
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class ApiClientTest {

    @Test
    fun `query products success`() {
        runBlocking {
            val products = ApiClient.apiService.queryProducts()

            Assertions.assertEquals(5, products.size)
            Assertions.assertEquals("1", products[0].id)
            Assertions.assertEquals("ABC123", products[0].sku)
            Assertions.assertEquals("Electronic Watch", products[0].name)
            Assertions.assertEquals(BigDecimal("299.99"), products[0].price)
            Assertions.assertEquals(ProductType.NORMAL, products[0].type)
            Assertions.assertEquals("image1.jpg", products[0].image)

        }

    }

    @Test
    fun `query inventories success`() {
        runBlocking {
            val inventories = ApiClient.apiService.queryInventories()

            Assertions.assertEquals(8, inventories.size)
            Assertions.assertEquals("1", inventories[0].id)
            Assertions.assertEquals("ABC123", inventories[0].sku)
            Assertions.assertEquals("CN_NORTH", inventories[0].zone)
            Assertions.assertEquals(120, inventories[0].quantity)

        }

    }

}