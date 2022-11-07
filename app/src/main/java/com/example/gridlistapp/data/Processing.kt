package com.example.gridlistapp.data


import com.google.gson.annotations.SerializedName

data class Processing(
    @SerializedName("status")
    val status: String
)