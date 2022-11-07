package com.example.gridlistapp.vewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gridlistapp.data.ImageData
import com.example.gridlistapp.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query

class ImageViewModel : ViewModel() {
    private var movieLiveData = MutableLiveData<ImageData>()
    fun getSearchImages(query: String) {
        RetrofitInstance.api.getSearchImage(query).enqueue(object  :
            Callback<ImageData> {
            override fun onResponse(call: Call<ImageData>, response: Response<ImageData>) {
                if (response.body()!=null){
                    movieLiveData.value = response.body()
                }
                else{
                    return
                }
            }
            override fun onFailure(call: Call<ImageData>, t: Throwable) {
                Log.d("TAG",t.message.toString())
            }
        })
    }
    fun observeMovieLiveData() : LiveData<ImageData> {
        return movieLiveData
    }
}