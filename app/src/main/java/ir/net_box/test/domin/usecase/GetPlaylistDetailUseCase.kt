package ir.net_box.test.domin.usecase

import ir.net_box.test.domin.NetboxRepository
import ir.net_box.test.domin.model.PlayDetail
import kotlinx.coroutines.flow.Flow

class GetPlaylistDetailUseCase(
    private val repository: NetboxRepository
) {
    suspend operator fun invoke(id: Int): Flow<PlayDetail> {
        return repository.getPlayListDetail(id = id)
    }
}