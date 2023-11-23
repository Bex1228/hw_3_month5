package com.example.hw_3_month5

import retrofit2.http.GET
import retrofit2.http.Query

interface PixaApi {

    @GET("api/")
    fun getImages(
        @Query("key") key: String = "40852738-c254766ec36cd421e6fe70cb9",
        @Query("q") keyWord: String,
        @Query("per_page") parePage: Int = 3,
        @Query("page") page: Int = 1
    ): retrofit2.Call<PixaModel>
}