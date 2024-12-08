package ir.net_box.test.domin.mapper

import ir.net_box.test.data.remote.dto.ChannelDto
import ir.net_box.test.data.remote.dto.PlayDetailDto
import ir.net_box.test.domin.model.Channel
import ir.net_box.test.domin.model.PlayDetail

fun PlayDetailDto.toPlayDetail():PlayDetail{
    return PlayDetail(
        thumbnail = thumbnail,
        channel = channelDto?.toChanel(),
        description = description,
        relatedPlaylistVideoCount = relatedPlaylistVideoCount ,
        fileSrc = fileSrc,
        fileSize = fileSize,
        relatedPlaylistWeight = relatedPlaylistWeight,
        quality =quality,
        duration =duration,
        file =file,
        contentType =contentType,
        relatedPlaylistId =relatedPlaylistId,
        name =name,
        id =id,
        createdDate =createdDate,
        publishDate =publishDate,
        channelId = channelId
    )
}

fun ChannelDto.toChanel(): Channel{
    return Channel(
        thumbnail =thumbnail,
        color =color,
        descriptionFa =descriptionFa,
        background =background,
        name =name,
        icon =icon,
        description =description,
        logo =logo,
        id =id,
        splash = splash
    )
}