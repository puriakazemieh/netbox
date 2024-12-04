package ir.net_box.test.domin.usecase

import androidx.paging.PagingData
import ir.net_box.test.domin.NetboxRepository
import ir.net_box.test.domin.model.Playlist
import kotlinx.coroutines.flow.Flow

class GetPlaylistUseCase(
    private val repository: NetboxRepository
) {
    suspend operator fun invoke(): Flow<PagingData<Playlist>> {
        return repository.getPlaylist(pageSize = 10)
    }
}