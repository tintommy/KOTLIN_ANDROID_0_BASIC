package com.example.retrofitdemo

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface AlbumService {
    @GET("/albums")
    suspend fun getAlbums(): Response<Albums>

    @GET("/albums")
    suspend fun getListAlbums(@Query("userId") userIds: List<Int>): Response<Albums> //lấy album theo nhiều userId

    @GET("/albums")
    suspend fun getSortedAlbums(@Query("userId") userId: Int): Response<Albums>//lấy album theo 1 userId

    @GET("/albums/{id}")
    suspend fun getAlbum(@Path(value = "id") albumId: Int): Response<AlbumsItem>


    @POST("/albums")
    suspend fun uploadAlbum(@Body album:AlbumsItem) :Response<AlbumsItem>
}