package ir.net_box.test.domin

import androidx.paging.PagingData
import ir.net_box.test.domin.model.Playlist
import kotlinx.coroutines.flow.Flow

interface NetboxRepository {
    suspend fun getPlaylist(pageSize: Int): Flow<PagingData<Playlist>>
}