package com.example.gridlistapp.data


import com.google.gson.annotations.SerializedName

data class ImageData(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
)