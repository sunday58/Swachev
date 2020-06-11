package com.swachev.model

data class Content(
    val address: Address,
    val category: String,
    val id: Int,
    val name: String,
    val products: List<Product>,
    val subCategory: String
)