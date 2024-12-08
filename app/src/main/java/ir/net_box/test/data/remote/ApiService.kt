package ir.net_box.test.data.remote

import ir.net_box.test.data.remote.dto.PlayDetailDto
import ir.net_box.test.data.remote.dto.PlaylistDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("api/v1/atv/playlist/{id}/")
    suspend fun getPlaylist(
        @Path("id") mediaId: Int,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Response<PlaylistDto>


    @GET("api/v1/atv/video/{id}/")
    suspend fun getPlayListDetail(
        @Path("id") mediaId: Int
    ): PlayDetailDto

}