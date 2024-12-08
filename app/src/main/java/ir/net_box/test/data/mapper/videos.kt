package ir.net_box.test.data.mapper

import ir.net_box.test.data.local.db.entity.VideosItemEntity
import ir.net_box.test.data.remote.dto.VideosItemDto

fun VideosItemEntity.toVideosItemDto() = VideosItemDto(
    duration = duration,
    thumbnail = thumbnail,
    file = file,
    contentType = contentType,
    name = name,
    description = description,
    id = id,
    createdDate = createdDate,
    fileSrc = fileSrc,
    publishDate = publishDate,
    fileSize = fileSize,
    quality = quality
)

fun VideosItemDto.toVideosItemEntity(page: Int,namePlayList: String?) = VideosItemEntity(
    duration = duration,
    thumbnail = thumbnail,
    file = file,
    contentType = contentType,
    name = name,
    namePlayList = namePlayList,
    description = description,
    id = id,
    createdDate = createdDate,
    fileSrc = fileSrc,
    publishDate = publishDate,
    fileSize = fileSize,
    quality = quality,
    page = page
)