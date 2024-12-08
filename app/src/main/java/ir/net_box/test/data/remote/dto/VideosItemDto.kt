package ir.net_box.test.data.remote.dto

import com.google.gson.annotations.SerializedName

data class VideosItemDto(

	@field:SerializedName("duration")
	val duration: Int? = null,

	@field:SerializedName("thumbnail")
	val thumbnail: String? = null,

	@field:SerializedName("file")
	val file: String? = null,

	@field:SerializedName("content_type")
	val contentType: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("created_date")
	val createdDate: String? = null,

	@field:SerializedName("file_src")
	val fileSrc: String? = null,

	@field:SerializedName("publish_date")
	val publishDate: String? = null,

	@field:SerializedName("file_size")
	val fileSize: String? = null,

	@field:SerializedName("quality")
	val quality: Int? = null,

	val namePlayList : String? = null,
)