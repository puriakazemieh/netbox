package ir.net_box.test.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PlayDetailDto(

	@field:SerializedName("thumbnail")
	val thumbnail: String? = null,

	@field:SerializedName("channel")
	val channelDto: ChannelDto? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("related_playlist_video_count")
	val relatedPlaylistVideoCount: Int? = null,

	@field:SerializedName("file_src")
	val fileSrc: String? = null,

	@field:SerializedName("file_size")
	val fileSize: Int? = null,

	@field:SerializedName("related_playlist_weight")
	val relatedPlaylistWeight: Int? = null,

	@field:SerializedName("quality")
	val quality: Int? = null,

	@field:SerializedName("duration")
	val duration: Int? = null,

	@field:SerializedName("file")
	val file: Any? = null,

	@field:SerializedName("content_type")
	val contentType: Int? = null,

	@field:SerializedName("related_playlist_id")
	val relatedPlaylistId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("created_date")
	val createdDate: String? = null,

	@field:SerializedName("publish_date")
	val publishDate: String? = null,

	@field:SerializedName("channel_id")
	val channelId: Int? = null
)

data class ChannelDto(

	@field:SerializedName("thumbnail")
	val thumbnail: String? = null,

	@field:SerializedName("color")
	val color: String? = null,

	@field:SerializedName("description_fa")
	val descriptionFa: String? = null,

	@field:SerializedName("background")
	val background: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("icon")
	val icon: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("logo")
	val logo: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("splash")
	val splash: String? = null
)
