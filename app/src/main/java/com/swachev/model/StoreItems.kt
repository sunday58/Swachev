package com.swachev.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.io.Serializable


@Entity(tableName = "store_table")
data class StoreItems(
    @PrimaryKey
    @TypeConverters(TypeConverter::class)
    val content: List<Content>,
    val last: Boolean,
    val page: Int,
    val size: Int,
    val totalElements: Int,
    val totalPages: Int
):Serializable