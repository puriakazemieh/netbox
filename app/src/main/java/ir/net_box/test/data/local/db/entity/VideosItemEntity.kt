package ir.net_box.test.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "video")
data class VideosItemEntity(
	val duration: Int? = null,
	val thumbnail: String? = null,
	val file: String? = null,
	val contentType: Int? = null,
	val name: String? = null,
	val description: String? = null,
	@PrimaryKey
	val id: Int? = null,
	val page: Int,
	val createdDate: String? = null,
	val fileSrc: String? = null,
	val publishDate: String? = null,
	val fileSize: String? = null,
	val quality: Int? = null
)