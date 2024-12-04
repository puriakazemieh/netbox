package ir.net_box.test.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PlaylistDto(

	@field:SerializedName("thumbnail")
	val thumbnail: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("videos_count")
	val videosCount: Int? = null,

	@field:SerializedName("videos")
	val videos: List<VideosItemDto>? = null,

	@field:SerializedName("id")
	val id: Int? = null
)