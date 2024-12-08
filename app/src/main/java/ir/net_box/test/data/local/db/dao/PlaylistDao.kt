package ir.net_box.test.data.local.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.net_box.test.data.local.db.entity.PlaylistEntity

@Dao
interface PlaylistDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(data: PlaylistEntity)

    @Query("SELECT * FROM playlist ")
    fun getAllData(): PagingSource<Int, PlaylistEntity>


}