package com.swachev.dataSource.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.swachev.model.Content
import com.swachev.model.StoreItems
import java.net.CacheResponse

@Dao
interface StoreDao {

    @Query("SELECT * FROM store_table")
    fun getStoreItems(): LiveData<List<Content?>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setStoreItems(response: List<Content>?)
}