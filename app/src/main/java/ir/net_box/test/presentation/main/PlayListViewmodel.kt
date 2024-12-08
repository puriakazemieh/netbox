package ir.net_box.test.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.net_box.test.domin.model.VideosItem
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

    private val _playlists = MutableStateFlow<PagingData<VideosItem>>(PagingData.empty())
    val playlists: StateFlow<PagingData<VideosItem>> = _playlists.asStateFlow()

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