package ir.net_box.test.domin.mapper

import ir.net_box.test.data.local.db.entity.VideosItemEntity
import ir.net_box.test.domin.model.VideosItem

fun VideosItemEntity.toVideosItem() = VideosItem(
    duration = duration,
    thumbnail = thumbnail,
    name = name,
    description = description,
    id = id,
    publishDate = publishDate,
)