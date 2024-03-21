package com.thoughtworks.kotlin_basic.api.dto

import com.google.gson.annotations.SerializedName
import com.thoughtworks.kotlin_basic.domain.ProductType
import java.math.BigDecimal

data class ProductDTO(
    val id: String,
    @SerializedName("SKU")
    val sku: String,
    val name: String,
    val price: BigDecimal,
    val type: ProductType,
    val image: String
)
