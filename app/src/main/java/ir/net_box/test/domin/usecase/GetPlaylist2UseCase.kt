package ir.net_box.test.domin.usecase

import androidx.paging.PagingData
import ir.net_box.test.domin.NetboxRepository
import ir.net_box.test.domin.model.VideosItem
import kotlinx.coroutines.flow.Flow

class GetPlaylist2UseCase(
    private val repository: NetboxRepository
) {
    suspend operator fun invoke(): Flow<PagingData<VideosItem>> {
        return repository.getPlaylist2(pageSize = 10)
    }

}