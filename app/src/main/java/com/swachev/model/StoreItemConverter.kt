package com.swachev.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class StoreItemConverter {

    @TypeConverter
    fun fromString(value: String): List<Content>? {
        val listType: Type = object: TypeToken<List<Content?>?>() {}.type
        return Gson().fromJson<List<Content>>(value, listType)
    }

    @TypeConverter
    fun listToString(list: List<Content?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}