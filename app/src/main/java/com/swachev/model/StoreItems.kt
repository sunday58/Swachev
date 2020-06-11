package com.swachev.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "store_table")
data class StoreItems(
    @PrimaryKey
    val content: List<Content>,
    val last: Boolean,
    val page: Int,
    val size: Int,
    val totalElements: Int,
    val totalPages: Int
)