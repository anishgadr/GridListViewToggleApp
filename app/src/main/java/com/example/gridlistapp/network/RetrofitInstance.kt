package com.example.gridlistapp.network

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

    var client = OkHttpClient.Builder().addInterceptor { chain ->
        val newRequest: Request = chain.request().newBuilder()
            .addHeader("Authorization", "Client-ID 1f7e95c7e235728")
            .build()
        chain.proceed(newRequest)
    }.build()



    val api : ImageApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.imgur.com/3/gallery/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ImageApi::class.java)
    }
}