package ir.net_box.test.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.net_box.test.domin.model.PlayDetail
import ir.net_box.test.domin.usecase.UseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayListDetailViewmodel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {


    private val _playDetail: MutableStateFlow<PlayDetail?> = MutableStateFlow(null)
    val playDetail: StateFlow<PlayDetail?> = _playDetail.asStateFlow()


    fun fetchPlaylists(id: Int) {
        viewModelScope.launch {
            useCase.getPlaylistDetailUseCase(id = id).collectLatest { pagingData ->
                _playDetail.value = pagingData
            }
        }
    }


}