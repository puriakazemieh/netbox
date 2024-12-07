package ir.net_box.test.presentation.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.net_box.test.domin.model.Playlist
import ir.net_box.test.domin.usecase.GetPlaylistUseCase
import ir.net_box.test.domin.usecase.UseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayListViewmodel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

    private val _playlists = MutableStateFlow<PagingData<Playlist>>(PagingData.empty())
    val playlists: StateFlow<PagingData<Playlist>> = _playlists.asStateFlow()

    init {
        fetchPlaylists()
    }

    private fun fetchPlaylists() {
        viewModelScope.launch {
            useCase.getPlaylistUseCase().collectLatest { pagingData ->
                _playlists.value = pagingData
            }
        }
    }


}