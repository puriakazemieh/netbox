package ir.net_box.test.data

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadParams
import androidx.paging.PagingSource.LoadResult
import androidx.paging.PagingState
import ir.net_box.test.data.remote.ApiService
import ir.net_box.test.data.remote.dto.VideosItemDto


class PlaylistRemoteDataSource(
    private val apiService: ApiService,
) : PagingSource<Int, VideosItemDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, VideosItemDto> {
        return try {
            val nextPageNumber = params.key ?: 1
            val apiResponse = apiService.getPlaylist(2, page = nextPageNumber, 10)
            val videos =
                apiResponse.body()?.videos?.map { it.copy(namePlayList = apiResponse.body()?.name) }
                    ?: emptyList()
            LoadResult.Page(
                data = videos,
                prevKey = null,
                nextKey = nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, VideosItemDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.prevKey?.minus(1)
        }
    }
}