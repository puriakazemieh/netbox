package ir.net_box.test.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.net_box.test.data.local.db.dao.PlaylistDao
import ir.net_box.test.data.local.db.dao.VideoDao
import ir.net_box.test.data.local.db.entity.PlaylistEntity
import ir.net_box.test.data.local.db.entity.VideosItemEntity

@Database(
    entities = [PlaylistEntity::class, VideosItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NetboxDatabase : RoomDatabase() {
    abstract fun playlistDao(): PlaylistDao
    abstract fun videoDao(): VideoDao
}