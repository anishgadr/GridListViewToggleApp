package com.example.gridlistapp.network

import com.example.gridlistapp.data.ImageData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ImageApi {
    @GET("search/{{sort}}/{{window}}/{{page}}?")
    fun getSearchImage(@Query("q") key : String) : Call<ImageData>
}