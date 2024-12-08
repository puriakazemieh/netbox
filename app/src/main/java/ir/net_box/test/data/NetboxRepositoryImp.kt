package ir.net_box.test.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import ir.net_box.test.data.local.db.NetboxDatabase
import ir.net_box.test.data.remote.ApiService
import ir.net_box.test.domin.NetboxRepository
import ir.net_box.test.domin.mapper.toVideosItem
import ir.net_box.test.domin.model.VideosItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NetboxRepositoryImp @Inject constructor(
    private val apiService: ApiService,
    private val database: NetboxDatabase
) : NetboxRepository {


    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getPlaylist(pageSize: Int): Flow<PagingData<VideosItem>> {
        val pagingSourceFactory = { database.videoDao().getAllData() }
        return Pager(
            config = PagingConfig(pageSize = pageSize, enablePlaceholders = false),
            remoteMediator = PlaylistRemoteMediator(apiService, database),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData ->
            pagingData.map { entity ->
                entity.toVideosItem()
            }
        }
    }

}