package com.swachev.ui.foryou

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.swachev.dataSource.BaseRepository
import com.swachev.dataSource.local.StoreDao
import com.swachev.dataSource.local.StoreRoomDatabase
import com.swachev.dataSource.remote.RetrofitBuilder
import com.swachev.model.Content
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
    var responseMessage = MutableLiveData<Event<Result<Content>>>()
    private val _store = MutableLiveData("")
    private val stores: LiveData<List<Content?>>


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

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun getStoresFromLocal(): LiveData<List<Content?>> {
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
                    response.body()?.content.let { repository.setStoreItems(it) }
                    Log.d("ForYou ", "${response.body()?.content.let { repository.setStoreItems(it) }}")
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