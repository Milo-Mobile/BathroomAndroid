package com.example.firstandroid.ViewModel.UpdateBathroom

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.firstandroid.BuildConfig.BuildConfig
import com.example.firstandroid.data.networking.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.math.BigDecimal

fun EditBathroom(): ViewModel {
    var state by mutableStateOf(EditBathroomData())

    suspend fun editBathroom() {
        val apiService: ApiService
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)
        apiService.editBathroomData(state)
    }
}


data class EditBathroomData(
    val title: String = "",
    val location: String = "",
    val capacity: Int = 0,
    var free: Boolean = false,
    val cost: BigDecimal = BigDecimal.ZERO,
    val hours: String = ""
)
