package com.swachev.dataSource.local

import androidx.lifecycle.LiveData
import com.swachev.model.Content

interface LocalRepository {

    fun getStoreItems(): LiveData<List<Content?>>
    suspend fun setStoreItems(response: List<Content>?)
}