package com.swachev.model

data class StoreItems(
    val content: List<Content>,
    val last: Boolean,
    val page: Int,
    val size: Int,
    val totalElements: Int,
    val totalPages: Int
)