package ir.net_box.test.domin.mapper

import ir.net_box.test.data.local.db.entity.PlaylistEntity
import ir.net_box.test.data.mapper.toVideosItemEntity
import ir.net_box.test.data.remote.dto.PlaylistDto
import ir.net_box.test.domin.model.Playlist

fun PlaylistEntity.toPlaylist() = Playlist(
    name = name,
//    videos = videos?.map { it.toVideosItem() },
)



fun PlaylistDto.toPlaylist() = Playlist(
    name = name,
    videos = videos?.map { it.toVideosItem() },
)