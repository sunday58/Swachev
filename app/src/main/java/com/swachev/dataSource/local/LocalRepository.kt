package com.swachev.dataSource.local

import androidx.lifecycle.LiveData
import com.swachev.model.Content
import com.swachev.model.Product

interface LocalRepository {

    fun getStoreItems(): LiveData<List<Content?>>
    suspend fun setStoreItems(response: List<Content>?)
}