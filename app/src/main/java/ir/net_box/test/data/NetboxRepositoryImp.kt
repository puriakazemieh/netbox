package ir.net_box.test.data

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import dagger.hilt.android.qualifiers.ApplicationContext
import ir.net_box.test.data.local.db.PlaylistDao
import ir.net_box.test.data.remote.ApiService
import ir.net_box.test.domin.NetboxRepository
import ir.net_box.test.domin.mapper.toPlaylist
import ir.net_box.test.domin.model.Playlist
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NetboxRepositoryImp @Inject constructor(
    private val apiService: ApiService,
    private val playlistDao: PlaylistDao
) : NetboxRepository {


    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getPlaylist(pageSize: Int): Flow<PagingData<Playlist>> {
        val pagingSourceFactory = { playlistDao.getAllData() }
        return Pager(
            config = PagingConfig(pageSize = pageSize, enablePlaceholders = false),
            remoteMediator = PlaylistRemoteMediator(apiService, playlistDao),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData ->
            pagingData.map { entity ->
                entity.toPlaylist()
            }
        }
    }

}