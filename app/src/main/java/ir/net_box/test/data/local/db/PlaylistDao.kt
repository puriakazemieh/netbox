package ir.net_box.test.data.local.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.net_box.test.data.local.db.entity.PlaylistEntity

@Dao
interface PlaylistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: PlaylistEntity)

    @Query("SELECT * FROM playlist ORDER BY id ASC")
    fun getAllData(): PagingSource<Int, PlaylistEntity>


}