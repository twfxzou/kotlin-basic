package com.thoughtworks.kotlin_basic.service

import com.thoughtworks.kotlin_basic.api.ApiClient
import com.thoughtworks.kotlin_basic.api.ProductApiService
import com.thoughtworks.kotlin_basic.domain.Inventory
import com.thoughtworks.kotlin_basic.domain.Product
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class ProductService(
    val productApiService: ProductApiService = ApiClient.productApiService
) {
    suspend fun getProducts(): Sequence<Product> = coroutineScope {
        val productDTOs = async { productApiService.queryProducts() }
        val inventoryDTOs = async { productApiService.queryInventories() }
        val skuToInventoriesMap = inventoryDTOs.await()
            .map { Inventory(it.id, it.sku, it.zone, it.quantity) }
            .groupBy { it.sku }
        val productDTOsResult = productDTOs.await()
        sequence {
            for (productDTO in productDTOsResult) {
                yield(
                    Product(
                        productDTO.id,
                        productDTO.sku,
                        productDTO.name,
                        productDTO.price,
                        productDTO.type,
                        productDTO.image,
                        skuToInventoriesMap[productDTO.sku]?.toMutableList() ?: mutableListOf()
                    )
                )
            }
        }
    }

}