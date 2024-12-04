package ir.net_box.test.data.remote

import ir.net_box.test.data.remote.dto.PlaylistDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/v1/atv/playlist/1/")
    suspend fun getPlaylist(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Response<PlaylistDto>

}