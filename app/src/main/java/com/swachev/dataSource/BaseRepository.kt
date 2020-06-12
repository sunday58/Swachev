package com.swachev.dataSource

import androidx.lifecycle.LiveData
import com.swachev.dataSource.local.LocalRepository
import com.swachev.dataSource.local.StoreDao
import com.swachev.dataSource.remote.RemoteRepository
import com.swachev.dataSource.remote.StoresApi
import com.swachev.model.Content
import com.swachev.model.StoreItems
import retrofit2.Call

class BaseRepository(private val api: StoresApi, private val dao: StoreDao):
    RemoteRepository, LocalRepository{
    override fun getRemoteStores(): Call<StoreItems> {
        return api.getStores()
    }

    override fun getStoreItems(): LiveData<List<StoreItems?>> {
        return dao.getStoreItems()
    }

    override suspend fun setStoreItems(response: StoreItems?) {
       return dao.setStoreItems(response)
    }
}