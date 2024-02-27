package com.example.firstandroid.Presentation.NewBathroom


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.firstandroid.BuildConfig.BuildConfig
import com.example.firstandroid.data.networking.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewBathroomViewModel() : ViewModel() {
    var state by mutableStateOf(NewBathroomData())
        private set

    fun updateTitle(title: String) {
        state = state.copy(title = title)
    }

    fun updateLocation(location: String) {
        state = state.copy(location = location)
    }

    fun updateCapacity(capacity: String) {
        capacity.toIntOrNull()?.let {
            state = state.copy(capacity = it)
        }
    }

    fun updateFree(free: Boolean) {
        state = state.copy(free = free)
    }

    fun updateCost(cost: String) {
        cost.toBigDecimalOrNull()?.let {
            state = state.copy(cost = it)
        }
    }

    fun updateHours(hours: String) {
        state = state.copy(hours = hours)
    }

    suspend fun addBathroom(state: NewBathroomData) {
        val apiService: ApiService
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)
        apiService.addBathroomData(state)
    }
}