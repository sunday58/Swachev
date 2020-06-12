package com.swachev.ui.foryou

import android.app.Application
import androidx.lifecycle.*
import com.swachev.dataSource.BaseRepository
import com.swachev.dataSource.local.StoreDao
import com.swachev.dataSource.local.StoreRoomDatabase
import com.swachev.dataSource.remote.RetrofitBuilder
import com.swachev.dataSource.remote.StoresApi
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

class ForYouViewModel(application: Application) : AndroidViewModel(application) {

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.IO)
    private val repository: BaseRepository
    private val dao: StoreDao = StoreRoomDatabase.getDatabase(application).storeDao()
    var responseMessage = MutableLiveData<Event<Result<StoreItems>>>()
    private val _store = MutableLiveData("")
    private val stores: LiveData<List<StoreItems?>>


    init {
        repository = BaseRepository(
            RetrofitBuilder.storeApi,
            dao
        )
        getStoresRemote()
        stores = Transformations.switchMap(StoreMediatorLiveData(_store)) {
            repository.getStoreItems()
        }
    }

    fun getStoresFromLocal(): LiveData<List<StoreItems?>> {
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
        repository.getRemoteStores().enqueue(object : Callback<StoreItems?> {
            override fun onResponse(call: Call<StoreItems?>, response: Response<StoreItems?>) {
                viewModelScope.launch {
                    response.body()?.let { repository.setStoreItems(it) }
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