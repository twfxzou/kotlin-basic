package com.thoughtworks.kotlin_basic.domain

import java.math.BigDecimal

class Product(
    val id: String,
    val sku: String,
    val name: String,
    val price: BigDecimal,
    val type: ProductType,
    val image: String,
    val inventories: MutableList<Inventory> = mutableListOf()
)
