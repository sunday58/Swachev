package com.swachev.ui.yourList

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.swachev.dataSource.BaseRepository
import com.swachev.dataSource.local.StoreDao
import com.swachev.dataSource.local.StoreRoomDatabase
import com.swachev.dataSource.remote.RetrofitBuilder
import com.swachev.model.Content
import com.swachev.model.Product
import com.swachev.model.StoreItems
import com.swachev.utility.Event
import com.swachev.utility.Result
import com.swachev.utility.State
import com.swachev.utility.StoreMediatorLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YourListViewModel(application: Application) : AndroidViewModel(application) {
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    var responseMessage = MutableLiveData<Event<Result<Product>>>()
    private val stores = MutableLiveData<List<Product?>>()


    init {

        getStoresRemote()
        stores.value = listOf()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
    fun getStoresFromLocal(): LiveData<List<Product?>> {
        return stores
    }

    private fun getStoresRemote(){
        responseMessage.postValue(
            Event(
                Result(
                    State.LOADING,
                    message = "Loading...",
                    isRefreshing = true
                )
            )
        )
       RetrofitBuilder.storeApi.getStores().enqueue(object : Callback<StoreItems?> {
            override fun onResponse(call: Call<StoreItems?>, response: Response<StoreItems?>) {
                viewModelScope.launch {
                    stores.value = response.body()!!.content[1].products
                    Log.d("YourList ", "${response.body()!!.content[1].products.let { stores }}")
                }
                responseMessage.postValue(
                    Event(
                        Result(
                            State.SUCCESS,
                            message = "Success",
                            isRefreshing = false
                        )
                    )
                )
            }

            override fun onFailure(call: Call<StoreItems?>, t: Throwable) {
                Log.d("dataError", t.localizedMessage!!)
                responseMessage.postValue(
                    Event(
                        Result(
                            State.ERROR,
                            message = "Check network connection",
                            error = t,
                            isRefreshing = false
                        )
                    )
                )
            }
        })
    }
}