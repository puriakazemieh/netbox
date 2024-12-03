package ir.net_box.test.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ir.net_box.test.data.local.db.VideoItemConverter


@Entity(tableName = "playlist")
data class PlaylistEntity(
	val thumbnail: String? = null,
	val name: String? = null,
	val description: String? = null,
	val videosCount: Int? = null,
    @TypeConverters(VideoItemConverter::class)
	val videos: List<VideosItemEntity>? = null,
	@PrimaryKey
	val id: Int? = null
)