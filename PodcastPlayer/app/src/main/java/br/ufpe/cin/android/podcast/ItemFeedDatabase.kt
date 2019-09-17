package br.ufpe.cin.android.podcast

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room



@Database(entities = [ItemFeed::class], version = 1)
abstract class ItemFeedDatabase : RoomDatabase() {
    abstract fun itemFeedDao(): ItemFeedDao

    companion object {
        private var instance: ItemFeedDatabase? = null
        private val sLock = Any()

        fun getInstance(context: Context): ItemFeedDatabase {
            synchronized (sLock) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ItemFeedDatabase::class.java, "ItemFeedDatabase.db"
                    ).build()
                }
                return instance!!
            }
        }
    }
}