package com.swachev.utility

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

class StoreMediatorLiveData<T>(query: LiveData<T>) : MediatorLiveData<T>() {
    init {
        addSource(query) {
            value = query.value
        }
    }
}