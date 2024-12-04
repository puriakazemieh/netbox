package ir.net_box.test.domin.mapper

import ir.net_box.test.data.local.db.entity.PlaylistEntity
import ir.net_box.test.domin.model.Playlist

fun PlaylistEntity.toPlaylist() = Playlist(
    name = name,
    videos = videos?.map { it.toVideosItem() },
)

