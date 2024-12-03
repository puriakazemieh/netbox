package ir.net_box.test.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ir.net_box.test.data.local.db.entity.PlaylistEntity

@Database(entities = [PlaylistEntity::class], version = 1, exportSchema = false)
@TypeConverters(VideoItemConverter::class)
abstract class NetboxDatabase : RoomDatabase() {
    abstract fun movieDao(): PlaylistDao
}