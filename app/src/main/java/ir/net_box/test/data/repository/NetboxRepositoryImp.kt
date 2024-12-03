package ir.net_box.test.data.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import ir.net_box.test.data.remote.ApiService
import ir.net_box.test.domin.NetboxRepository
import ir.net_box.test.domin.model.Playlist
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NetboxRepositoryImp @Inject constructor(
    private val apiService: ApiService,
    @ApplicationContext context: Context
) : NetboxRepository {


//    override suspend fun getPlaylist(): Flow<Playlist> {
//
//    }

}