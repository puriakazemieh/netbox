package ir.net_box.test.data.mapper

import ir.net_box.test.data.local.db.entity.PlaylistEntity
import ir.net_box.test.data.remote.dto.PlaylistDto

fun PlaylistEntity.toPlaylistDto() = PlaylistDto(
    thumbnail = thumbnail,
    name = name,
    description = description,
    videosCount = videosCount,
    videos = videos?.map { it.toVideosItemDto() },
    id = id
)

fun PlaylistDto.toPlaylistEntity() = PlaylistEntity(
    thumbnail = thumbnail,
    name = name,
    description = description,
    videosCount = videosCount,
    videos = videos?.map { it.toVideosItemEntity() },
    id = id
)
