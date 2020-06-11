package com.swachev.dataSource.remote

import com.swachev.model.StoreItems
import retrofit2.Call

interface RemoteRepository {

    fun getRemoteStores(): Call<StoreItems>
}