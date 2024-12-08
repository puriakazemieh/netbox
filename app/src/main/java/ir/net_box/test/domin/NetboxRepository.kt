package ir.net_box.test.domin

import androidx.paging.PagingData
import ir.net_box.test.domin.model.PlayDetail
import ir.net_box.test.domin.model.VideosItem
import kotlinx.coroutines.flow.Flow

interface NetboxRepository {
    suspend fun getPlaylist(pageSize: Int): Flow<PagingData<VideosItem>>
    suspend fun getPlaylist2(pageSize: Int): Flow<PagingData<VideosItem>>
    suspend fun getPlayListDetail(id: Int): Flow<PlayDetail>
}