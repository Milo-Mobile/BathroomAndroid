package com.example.firstandroid.presentation.get_bathroom

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstandroid.build_config.BuildConfig
import com.example.firstandroid.data.repository.BathroomRepository
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GetBathroomViewModel() : ViewModel() {

    var state by mutableStateOf(GetBathroomDataList())
        private set

    private lateinit var bathroomRepository: BathroomRepository

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun onStart() {
        bathroomRepository = retrofit.create(BathroomRepository::class.java)
        viewModelScope.launch {
            getAllBathroom()
        }
    }

    private suspend fun getAllBathroom() {
        state = state.copy(
            getBathroomList = bathroomRepository.getAllBathroomData().body()?.data ?: emptyList()
        )
    }
}