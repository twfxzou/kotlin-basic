package com.thoughtworks.kotlin_basic.service

import com.thoughtworks.kotlin_basic.api.ProductApiService
import com.thoughtworks.kotlin_basic.api.dto.InventoryDTO
import com.thoughtworks.kotlin_basic.api.dto.ProductDTO
import com.thoughtworks.kotlin_basic.domain.ProductType
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class ProductServiceTest {

    @Test
    fun `should group inventory success when get product`() = runBlocking {
        val productApiService = mockk<ProductApiService>()
        val productService = ProductService(productApiService)

        coEvery { productApiService.queryProducts() } returns listOf(
            ProductDTO("1", "S001", "test", BigDecimal("20.1"), ProductType.NORMAL, "test.jpg"),
            ProductDTO("2", "S002", "test2", BigDecimal("20.2"), ProductType.NORMAL, "test.jpg"),
        )
        coEvery { productApiService.queryInventories() } returns listOf(
            InventoryDTO("1", "S001", "CN", 100),
            InventoryDTO("2", "S001", "US", 200),
            InventoryDTO("3", "S002", "US", 100),
        )

        val products = productService.getProducts().toList()

        assertEquals(2, products.size)

        assertEquals("1", products[0].id)
        assertEquals(2, products[0].inventories.size)
        assertEquals("1", products[0].inventories[0].id)
        assertEquals("2", products[0].inventories[1].id)

        assertEquals("2", products[1].id)
        assertEquals(1, products[1].inventories.size)
        assertEquals("3", products[1].inventories[0].id)
    }

    @Test
    fun `should no inventory when sku not match`() = runBlocking {
        val productApiService = mockk<ProductApiService>()
        val productService = ProductService(productApiService)

        coEvery { productApiService.queryProducts() } returns listOf(
            ProductDTO("1", "S003", "test", BigDecimal("20.1"), ProductType.NORMAL, "test.jpg"),
        )
        coEvery { productApiService.queryInventories() } returns listOf(
            InventoryDTO("1", "S001", "CN", 100),
            InventoryDTO("2", "S001", "US", 200),
            InventoryDTO("3", "S002", "US", 100),
        )

        val products = productService.getProducts().toList()

        assertEquals(1, products.size)

        assertEquals("1", products[0].id)
        assertEquals(0, products[0].inventories.size)
    }
}