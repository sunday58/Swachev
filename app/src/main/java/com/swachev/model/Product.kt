package com.swachev.model

data class Product(
    val attributes: List<Attribute>,
    val category: String,
    val currency: String,
    val description: String,
    val name: String,
    val rate: Double,
    val unit: String
)