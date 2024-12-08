package ir.net_box.test.data.local.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.net_box.test.data.local.db.entity.VideosItemEntity

@Dao
interface VideoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(data: VideosItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: List<VideosItemEntity>)

    @Query("DELETE FROM video ")
    suspend fun clearAll()

    @Query("SELECT * FROM video  ORDER BY page ASC ")
    fun getAllData(): PagingSource<Int, VideosItemEntity>

    @Query("SELECT * FROM video ")
    fun getAllDataList(): List<VideosItemEntity>


}