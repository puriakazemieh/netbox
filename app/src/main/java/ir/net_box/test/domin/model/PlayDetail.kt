package ir.net_box.test.domin.model

data class PlayDetail(
	val thumbnail: String? = null,
	val channel: Channel? = null,
	val description: String? = null,
	val relatedPlaylistVideoCount: Int? = null,
	val fileSrc: String? = null,
	val fileSize: Int? = null,
	val relatedPlaylistWeight: Int? = null,
	val quality: Int? = null,
	val duration: Int? = null,
	val file: Any? = null,
	val contentType: Int? = null,
	val relatedPlaylistId: Int? = null,
	val name: String? = null,
	val id: Int? = null,
	val createdDate: String? = null,
	val publishDate: String? = null,
	val channelId: Int? = null
)

data class Channel(
	val thumbnail: String? = null,
	val color: String? = null,
	val descriptionFa: String? = null,
	val background: String? = null,
	val name: String? = null,
	val icon: String? = null,
	val description: String? = null,
	val logo: String? = null,
	val id: Int? = null,
	val splash: String? = null
)
