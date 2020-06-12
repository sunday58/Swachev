package com.swachev.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.io.Serializable

@Entity(tableName = "store_table")
data class Content(
    @PrimaryKey
    val address: Address,
    val category: String,
    val id: Int,
    val name: String,
    @TypeConverters(TypeConverter::class)
    val products: List<Product>,
    val subCategory: String
):Serializable