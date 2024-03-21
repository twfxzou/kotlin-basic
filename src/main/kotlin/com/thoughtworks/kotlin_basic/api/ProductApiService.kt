package com.thoughtworks.kotlin_basic.api

import com.thoughtworks.kotlin_basic.api.dto.InventoryDTO
import com.thoughtworks.kotlin_basic.api.dto.ProductDTO
import retrofit2.http.GET

interface ProductApiService {
    @GET("/products")
    suspend fun queryProducts(): List<ProductDTO>
    @GET("/inventories")
    suspend fun queryInventories(): List<InventoryDTO>
}