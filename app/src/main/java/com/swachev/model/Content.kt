package com.swachev.model

import androidx.room.*
import java.io.Serializable


@Entity(tableName = "store_table")
data class Content(
    @PrimaryKey
    @Embedded
    val address: Address,
    val category: String,
    val id: Int,
    val name: String,
    @TypeConverters(StoreItemConverter::class)
    val products: List<Product>,
    val subCategory: String
):Serializable