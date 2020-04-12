package ru.yushka.firstotushomework.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET


class MainViewModel : ViewModel() {
    val screenState = MutableLiveData<ScreenState>()
//    private val retrofit: Retrofit = Retrofit.Builder()
//        .baseUrl("https://thereportoftheweek-api.herokuapp.com/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//    private val api: API = retrofit.create()
//
//    init {
//        viewModelScope.launch {
//            screenState.value = ScreenState.Progress
//            try {
//                screenState.value = ScreenState.Content(api.getReports())
//            } catch (ex: Exception) {
//                Log.e("MainViewModel", "", ex)
//                screenState.value = ScreenState.Error
//            }
//        }
//    }
//
//    interface API {
//        @GET("reports")
//        suspend fun getReports(): List<VideoItem>
//    }

    sealed class ScreenState {
        object Progress : ScreenState()
        object Error : ScreenState()
        class Content(val list: List<VideoItem>) : ScreenState()
    }
}
