package ir.net_box.test.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import ir.net_box.test.data.local.db.PlaylistDao
import ir.net_box.test.data.local.db.entity.PlaylistEntity
import ir.net_box.test.data.mapper.toPlaylistEntity
import ir.net_box.test.data.remote.ApiService


@OptIn(ExperimentalPagingApi::class)
class PlaylistRemoteMediator(
    private val apiService: ApiService,
    private val myDataDao: PlaylistDao
) : RemoteMediator<Int, PlaylistEntity>() {


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PlaylistEntity>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull()
                if (lastItem == null) return MediatorResult.Success(endOfPaginationReached = true)
                state.pages.size + 1
            }
        }

        return try {
            val response = apiService.getPlaylist(page, state.config.pageSize)
            if (response.isSuccessful) {
                response.body()?.let { data ->
                    myDataDao.insertAll(data.toPlaylistEntity())
                }
                MediatorResult.Success(endOfPaginationReached = response.body()?.videos.isNullOrEmpty())
            } else {
                MediatorResult.Error(Exception("Failed to load data"))
            }
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}