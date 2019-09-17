package br.ufpe.cin.android.podcast

import androidx.room.*

@Dao
interface ItemFeedDao {
    @Query("SELECT * FROM ItemFeed")
    fun getAll(): List<ItemFeed>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg itemFeeds: ItemFeed)
}