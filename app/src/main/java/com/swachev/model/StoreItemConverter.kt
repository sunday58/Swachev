package com.swachev.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class StoreItemConverter {

    @TypeConverter
    fun fromString(value: String): List<Product>? {
        val listType: Type = object: TypeToken<List<Product?>?>() {}.type
        return Gson().fromJson<List<Product>>(value, listType)
    }

    @TypeConverter
    fun listToString(list: List<Product?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

}