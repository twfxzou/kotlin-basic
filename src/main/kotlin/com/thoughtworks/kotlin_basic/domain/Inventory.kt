package com.thoughtworks.kotlin_basic.domain

data class Inventory(
    val id: String,
    val sku: String,
    val zone: String,
    val quantity: Int
)
