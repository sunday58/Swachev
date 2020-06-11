package com.swachev.dataSource.local

import androidx.lifecycle.LiveData
import com.swachev.model.StoreItems

interface LocalRepository {

    fun getStoreItems(): LiveData<StoreItems?>
    suspend fun setStoreItems(response: StoreItems?)
}