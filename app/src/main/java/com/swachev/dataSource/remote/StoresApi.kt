package com.swachev.dataSource.remote

import com.swachev.model.StoreItems
import retrofit2.Call
import retrofit2.http.GET

interface StoresApi {
@GET("api/v1/store/all")
fun getStores(): Call<StoreItems>

}