package com.thoughtworks.kotlin_basic.domain

import java.math.BigDecimal

data class Product(
    val id: String,
    val sku: String,
    val name: String,
    val price: BigDecimal,
    val type: ProductType,
    val image: String,
    val inventories: MutableList<Inventory>
) {
    fun getInventoryQuantity() = inventories.sumOf { it.quantity }

    fun getRealPrice() = when(type) {
        ProductType.NORMAL -> price
        ProductType.HIGH_DEMAND -> when(getInventoryQuantity()) {
            in 31..100 -> price * BigDecimal("1.2")
            in 0..30 -> price * BigDecimal("1.5")
            else -> price
        }
    }
}
