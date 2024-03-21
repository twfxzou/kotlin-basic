package com.thoughtworks.kotlin_basic.api.dto

import com.google.gson.annotations.SerializedName

data class InventoryDTO(
    val id: String,
    @SerializedName("SKU")
    val sku: String,
    val zone: String,
    val quantity: Int
)
