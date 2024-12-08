package ir.net_box.test.data

import android.annotation.SuppressLint
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import ir.net_box.test.data.local.db.NetboxDatabase
import ir.net_box.test.data.local.db.entity.VideosItemEntity
import ir.net_box.test.data.mapper.toVideosItemEntity
import ir.net_box.test.data.remote.ApiService


@OptIn(ExperimentalPagingApi::class)
class PlaylistRemoteMediator(
    private val apiService: ApiService,
    private val database: NetboxDatabase
) : RemoteMediator<Int, VideosItemEntity>() {

    @SuppressLint("SuspiciousIndentation")
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, VideosItemEntity>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull()
                if (lastItem == null) return MediatorResult.Success(endOfPaginationReached = true)
                lastItem.page + 1
            }
        }

        return try {
            val response = apiService.getPlaylist(page, state.config.pageSize)
            if (response.isSuccessful) {
                val videos = response.body()?.videos ?: emptyList()

                database.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        database.videoDao().clearAll()
                    }

                    database.videoDao().insertAll(
                        videos.map { it.toVideosItemEntity(page,response.body()?.name) }
                    )
                }

                MediatorResult.Success(endOfPaginationReached = videos.isEmpty())
            } else {
                MediatorResult.Error(Exception("Failed to load data"))
            }
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}