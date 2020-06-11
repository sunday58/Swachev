package com.swachev.dataSource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.swachev.model.StoreItemConverter
import com.swachev.model.StoreItems


@Database(
    exportSchema = false,
    version = 1,
    entities = [StoreItems::class])
@TypeConverters(StoreItemConverter::class)
abstract class StoreRoomDatabase  : RoomDatabase(){
    abstract fun storeDao(): StoreDao

    companion object {
        @Volatile
        private var INSTANCE: StoreRoomDatabase? = null

        fun getDatabase(context: Context): StoreRoomDatabase {
            val instance = INSTANCE
            if (instance != null) {
                return instance
            }
            synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    StoreRoomDatabase::class.java,
                    "store_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE =  newInstance
                return newInstance
            }
        }
    }
}